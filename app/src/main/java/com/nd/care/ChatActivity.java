package com.nd.care;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.nd.care.adapter.ChatAdapter;
import com.nd.care.bean.ChatMessage;
import com.withliyh.mylib.viewholder.QuickAdapter;

import java.util.ArrayList;

public class ChatActivity extends Activity {

    private ListView mListView;
    private ArrayList<ChatMessage> mDatas = new ArrayList<ChatMessage>();

    private QuickAdapter<ChatMessage> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.id_lv_main);


        initDatas();


        mAdapter = new ChatAdapter(ChatActivity.this, mDatas);
        mListView.setAdapter(mAdapter);
        mListView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mListView.smoothScrollToPosition(mDatas.size() - 1);
            }
        }, 1000);

    }

    private void initDatas() {
        ChatMessage msg = null;
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);

        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        for (int i = 0; i < 1000; i++) {
            ChatMessage msg2 = null;
            if (i % 2 == 0) {
                msg2 = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                        null, false);
            } else {
                msg2 = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                        null, true);
            }

            mDatas.add(msg2);
        }
    }

}
