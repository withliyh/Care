package com.withliyh.mylib.viewholder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public abstract class QuickAdapter<T> extends BaseQuickAdapter<T> {

    private BaseViewHolder mLoadMoreViewHolder;

    public QuickAdapter(Context context, int layoutId, List<T> datas) {
        super(context, layoutId, datas);
    }

    public QuickAdapter(Context context, SupportType<T> typeSupport, List<T> datas) {
        super(context, typeSupport, datas);
    }

    @Override
    public BaseViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        if (getItemViewType(position) == BaseViewHolder.FIRST_TYPE_ITEM) {
            return getLoadHolder(mContext, convertView, parent);
        }
        if (mTypeSupport != null) {
            int layoutId = mTypeSupport.getLayoutId(position, mDatas.get(position));
            return get(mContext, convertView, parent, layoutId, position);
        }
        return get(mContext, convertView, parent, mLayoutId, position);
    }

    private BaseViewHolder getLoadHolder(Context context, View convertView, ViewGroup parent) {
        if (convertView == null) {
            SupportLoad supportLoad = (SupportLoad) mLoadMoreViewHolder;
            return supportLoad.createHolder(context, parent);
        }
        return (BaseViewHolder) convertView.getTag();
    }

    private BaseViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            BaseViewHolder viewHolder = new BaseViewHolder(context, parent, layoutId, position);
            initViewExtra(viewHolder);
            return viewHolder;
        }
        BaseViewHolder existHolder = (BaseViewHolder) convertView.getTag();
//        if (existHolder.getLayoutId() != layoutId) {
//            BaseViewHolder viewHolder = new BaseViewHolder(context, parent, layoutId, position);
//            initViewExtra(viewHolder);
//            return viewHolder;
//        }
        return existHolder;
    }

    public void setLoadMoreViewHolder(BaseViewHolder holder) {
        mEnableLoadMore = true;
        mLoadMoreViewHolder = holder;
    }

    /**
     * 设置监听器、属性等操作
     */
    protected void initViewExtra(BaseViewHolder helper) {
    }

}
