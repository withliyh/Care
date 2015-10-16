package com.withliyh.mylib.viewholder;

import android.view.View;

/**
 * Created by Administrator on 2015/10/15.
 */
public abstract class LoadMoreHolder<T> extends BaseViewHolder implements SupportLoad{
    private boolean isLoading = false;
    protected QuickAdapter<T> mAdapter;

    public QuickAdapter<T> getAdapter() {
        return mAdapter;
    }

    @Override
    public View getConvertView() {
        if (!isLoading) {
            onLoading(this);
            isLoading = true;
        }
        return super.getConvertView();
    }

    public void notifyLoadSuccess() {
        isLoading = false;
        onLoadSuccess(this);
    }

    public void notifyLoadFailure() {
        isLoading = false;
        onLoadFailure(this);
    }

}
