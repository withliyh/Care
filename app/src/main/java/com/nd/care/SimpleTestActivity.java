package com.nd.care;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.nd.care.bean.MeiZiTu;
import com.withliyh.mylib.http.GsonGetRequest;
import com.withliyh.mylib.http.VolleyEx;
import com.withliyh.mylib.viewholder.BaseViewHolder;
import com.withliyh.mylib.viewholder.QuickAdapter;

import java.util.LinkedList;

public class SimpleTestActivity extends Activity {


    private ListView mListView;
    private LinkedList<MeiZiTu.Data> mDatas = new LinkedList<MeiZiTu.Data>();
    private SwipeRefreshLayout mSwipe;
    private QuickAdapter<MeiZiTu.Data> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        mSwipe.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        mListView = (ListView) findViewById(R.id.id_lv_main);
        mAdapter = new QuickAdapter<MeiZiTu.Data>(
                SimpleTestActivity.this, R.layout.item_list, mDatas) {

            @Override
            public void convert(BaseViewHolder helper, MeiZiTu.Data item) {
                helper.setImageUrl(R.id.imageview, item.image.small);
            }

        };

        // 设置适配器
        mListView.setAdapter(mAdapter);

        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                VolleyEx.getInstance().addRequest(req);
            }
        });
    }


    GsonGetRequest<MeiZiTu> req = ApiRequest.getMeiZiTuRequest(new Response.Listener<MeiZiTu>() {
        @Override
        public void onResponse(MeiZiTu response) {
            mSwipe.setRefreshing(false);
            mDatas.addAll(response.data);
            mAdapter.notifyDataSetChanged();
        }
    },
    new Response.ErrorListener() {

        @Override
        public void onErrorResponse(VolleyError error) {

        }
    });


}
