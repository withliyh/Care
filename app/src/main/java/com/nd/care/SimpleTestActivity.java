package com.nd.care;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nd.care.bean.MeiZiTu;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.withliyh.mylib.viewholder.BaseViewHolder;
import com.withliyh.mylib.viewholder.LoadMoreHolder;
import com.withliyh.mylib.viewholder.QuickAdapter;
import com.withliyh.mylib.viewholder.SimpleLoadHolder;

import java.io.IOException;
import java.util.LinkedList;

public class SimpleTestActivity extends Activity {

    static int mCur = 0;
    static int mFootCur = 0;
    private ListView mListView;
    private LinkedList<MeiZiTu.Data> mDatas = new LinkedList<MeiZiTu.Data>();
    private SwipeRefreshLayout mSwipe;
    private QuickAdapter<MeiZiTu.Data> mAdapter;

    private final static String url = "http://api.lovebizhi.com/macos_v4.php?a=category&spdy=1&tid=3&order=hot&color_id=3&device=105&uuid=436e4ddc389027ba3aef863a27f6e6f9&mode=0&retina=0&client_id=1008&device_id=31547324&model_id=105&size_id=0&channel_id=70001&screen_width=1920&screen_height=1200&bizhi_width=1920&bizhi_height=1200&version_code=19&language=zh-Hans&jailbreak=0&mac=&p=1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        mSwipe.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        initDatas(mCur);

        mListView = (ListView) findViewById(R.id.id_lv_main);
        mAdapter = new QuickAdapter<MeiZiTu.Data>(
                SimpleTestActivity.this, R.layout.item_list, mDatas) {

            @Override
            public void convert(BaseViewHolder helper, MeiZiTu.Data item) {
                helper.setImageUrl(R.id.imageview, item.image.small);
            }

            @Override
            protected void initViewExtra(BaseViewHolder helper) {

            }

        };

        LoadMoreHolder loadHolder = new SimpleLoadHolder<MeiZiTu.Data>(mAdapter, R.layout.loadmorelayout) {

            @Override
            public void onLoading(BaseViewHolder helper) {
                helper.setText(R.id.txtLoadmore, "正在加载");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initFootDatas(mFootCur);
                        getAdapter().notifyDataSetChanged();
                        notifyLoadSuccess();
                    }
                }, 3000);
            }

            @Override
            public void onLoadSuccess(BaseViewHolder helper) {
                helper.setText(R.id.txtLoadmore, "正在成功");
            }

            @Override
            public void onLoadFailure(BaseViewHolder helper) {
                helper.setText(R.id.txtLoadmore, "正在失败");
            }
        };

        mAdapter.setLoadMoreViewHolder(loadHolder);
        // 设置适配器
        mListView.setAdapter(mAdapter);

        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipe.setRefreshing(false);
                        initDatas(mCur);
                        mAdapter.notifyDataSetChanged();
                    }
                }, 3000);
            }
        });



        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Toast.makeText(SimpleTestActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String jsonstr = response.body().string();
                Gson gson = new Gson();
                MeiZiTu meizitu = gson.fromJson(jsonstr, MeiZiTu.class);
                mDatas.addAll(meizitu.data);
                new Handler(getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                    }
                });

//                for (MeiZiTu.Data e : meizitu.data) {
//                    Log.d("MEIZITU", e.image.diy);
//                }
            }
        });

    }

    private void initDatas(int cur) {
//        for (int i = cur + 1; i <= cur + 10; i++) {
//            Bean bean = null;
//            bean = new Bean("美女一只", "周三早上捡到妹子一只，在食堂二楼", String.valueOf(i), "20130240122");
//            mDatas.addFirst(bean);
//            mCur = i;
//        }

    }

    private void initFootDatas(int cur) {
//        for (int i = cur - 1; i >= cur - 10; i--) {
//            Bean bean = null;
//            bean = new Bean("美女一只", "周三早上捡到妹子一只，在食堂二楼", String.valueOf(i), "20130240122");
//            mDatas.addLast(bean);
//            mFootCur = i;
//        }

    }

}
