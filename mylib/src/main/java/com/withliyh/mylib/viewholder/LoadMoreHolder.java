package com.withliyh.mylib.viewholder;

import android.view.View;

/**
 * Created by Administrator on 2015/10/15.
 */
public abstract class LoadMoreHolder extends BaseViewHolder implements SupportLoad{

    @Override
    public View getConvertView() {
        onLoading(this);
        return super.getConvertView();
    }

    public void notifyLoadSuccess() {
        onLoadSuccess(this);
    }

    public void notifyLoadFailure() {
        onLoadFailure(this);
    }

}
