package com.nd.care;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;

import com.withliyh.mylib.viewholder.BaseViewHolder;
import com.withliyh.mylib.viewholder.LoadMoreHolder;
import com.withliyh.mylib.viewholder.QuickAdapter;
import com.withliyh.mylib.viewholder.SimpleLoadHolder;

import java.util.LinkedList;

public class SimpleTestActivity extends Activity {

    static int mCur = 0;
    static int mFootCur = 0;
    private ListView mListView;
    private LinkedList<Bean> mDatas = new LinkedList<Bean>();
    private SwipeRefreshLayout mSwipe;
    private QuickAdapter<Bean> mAdapter;

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
        mAdapter = new QuickAdapter<Bean>(
                SimpleTestActivity.this, R.layout.item_list, mDatas) {

            @Override
            public void convert(BaseViewHolder helper, Bean item) {
                helper.setText(R.id.tv_title, item.getTitle());
                helper.setText(R.id.tv_describe, item.getDesc());
                helper.setText(R.id.tv_phone, item.getPhone());
                helper.setText(R.id.tv_time, item.getTime());
            }

            @Override
            protected void initViewExtra(BaseViewHolder helper) {
                helper.getView(R.id.tv_title).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }

        };

        LoadMoreHolder loadHolder = new SimpleLoadHolder<Bean>(mAdapter, R.layout.loadmorelayout) {

            @Override
            public void onLoading(BaseViewHolder helper) {
                helper.setText(R.id.txtLoadmore, "正在加载");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initFootDatas(mFootCur);
                        getAdapter().notifyDataSetChanged();
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

    }

    private void initDatas(int cur) {
        for (int i = cur + 1; i <= cur + 10; i++) {
            Bean bean = null;
            bean = new Bean("美女一只", "周三早上捡到妹子一只，在食堂二楼", String.valueOf(i), "20130240122");
            mDatas.addFirst(bean);
            mCur = i;
        }

    }

    private void initFootDatas(int cur) {
        for (int i = cur - 1; i >= cur - 10; i--) {
            Bean bean = null;
            bean = new Bean("美女一只", "周三早上捡到妹子一只，在食堂二楼", String.valueOf(i), "20130240122");
            mDatas.addLast(bean);
            mFootCur = i;
        }

    }

}
