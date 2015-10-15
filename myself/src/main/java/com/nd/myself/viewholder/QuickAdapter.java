package com.nd.myself.viewholder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public abstract class QuickAdapter<T> extends BaseQuickAdapter<T> {


    public QuickAdapter(Context context, int layoutId, List<T> datas) {
        super(context, layoutId, datas);
    }

    public QuickAdapter(Context context, TypeSupport<T> typeSupport, List<T> datas) {
        super(context, typeSupport, datas);
    }

    @Override
    public BaseViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        if (mTypeSupport != null) {
            int layoutId = mTypeSupport.getLayoutId(position, mDatas.get(position));
            return get(mContext, convertView, parent, layoutId, position);
        }
        return get(mContext, convertView, parent, mLayoutId, position);
    }


    private BaseViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            BaseViewHolder viewHolder =  new BaseViewHolder(context, parent, layoutId, position);
            initViewExtra(viewHolder);
            return viewHolder;
        }
        BaseViewHolder existHolder =  (BaseViewHolder) convertView.getTag();
        if (existHolder.getLayoutId() != layoutId) {
            BaseViewHolder viewHolder =  new BaseViewHolder(context, parent, layoutId, position);
            initViewExtra(viewHolder);
            return viewHolder;
        }
        return existHolder;
    }
    /**
     * 设置监听器、属性等操作
     */
    protected void initViewExtra(BaseViewHolder helper) {}

}
