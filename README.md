# 通用适配器库
-----
使用最简单的方式实现 AbsListView 的适配器

### 简单适配器
```Java
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
```

### 支持多类型项的适配器
 
 ```Java
 
 package com.nd.care.adapter;
 
 import android.content.Context;
 
 import com.nd.care.bean.ChatMessage;
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
 
```

### 支持加载更多

```Java
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

```