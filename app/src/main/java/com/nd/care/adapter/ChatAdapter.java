package com.nd.care.adapter;

import android.content.Context;

import com.nd.care.ChatMessage;
import com.nd.care.R;
import com.withliyh.mylib.viewholder.BaseViewHolder;
import com.withliyh.mylib.viewholder.QuickAdapter;
import com.withliyh.mylib.viewholder.TypeSupport;

import java.util.List;

/**
 * Created by Administrator on 2015/10/14.
 */
public class ChatAdapter extends QuickAdapter<ChatMessage> {

    public ChatAdapter(Context context, List<ChatMessage> datas) {
        super(context, new MultiItemTypeSupport(), datas);
    }

    @Override
    protected void initViewExtra(BaseViewHolder helper) {
        switch (helper.getLayoutId()) {
            case R.layout.main_chat_from_msg:
                break;
            case R.layout.main_chat_send_msg:
                break;
        }
    }

    @Override
    public void convert(BaseViewHolder helper, ChatMessage item) {
        switch (helper.getLayoutId()) {
            case R.layout.main_chat_from_msg:
                helper.setText(R.id.chat_from_content, item.getContent());
                helper.setText(R.id.chat_from_name, item.getName());
                helper.setImageResource(R.id.chat_from_icon, item.getIcon());
                break;
            case R.layout.main_chat_send_msg:
                helper.setText(R.id.chat_send_content, item.getContent());
                helper.setText(R.id.chat_send_name, item.getName());
                helper.setImageResource(R.id.chat_send_icon, item.getIcon());
                break;
        }

    }

    private static class MultiItemTypeSupport implements TypeSupport<ChatMessage> {
        @Override
        public int getLayoutId(int position, ChatMessage msg) {
            if (msg.isComMeg()) {
                return R.layout.main_chat_from_msg;
            }
            return R.layout.main_chat_send_msg;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int postion, ChatMessage msg) {
            if (msg.isComMeg()) {
                return ChatMessage.RECIEVE_MSG;
            }
            return ChatMessage.SEND_MSG;
        }
    }
}
