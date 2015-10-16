package com.withliyh.mylib.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by liy on 15-10-16.
 */
public abstract class SimpleLoadHolder<T> extends LoadMoreHolder {
    private QuickAdapter<T> mAdapter;

    public SimpleLoadHolder(QuickAdapter<T> adapter, int layoutId) {
        this.mLayoutId = layoutId;
        this.mAdapter = adapter;
    }

    public QuickAdapter<T> getAdapter() {
        return mAdapter;
    }

    @Override
    public BaseViewHolder createHolder(Context context, ViewGroup parent) {
        mConvertView = LayoutInflater.from(context).inflate(mLayoutId, parent, false);
        mConvertView.setTag(this);
        return this;
    }

}
