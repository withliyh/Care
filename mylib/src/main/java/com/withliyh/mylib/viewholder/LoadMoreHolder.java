package com.withliyh.mylib.viewholder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2015/10/15.
 */
public abstract class LoadMoreHolder extends BaseViewHolder implements SupportLoad{
    public LoadMoreHolder(Context context, ViewGroup parent, int layoutId, int position) {

        super(context, parent, layoutId, position);
    }

    @Override
    public View getConvertView() {
        onLoading(this);
        return super.getConvertView();
    }

    public void notifyLoadSuccess() {
        onLoadSuccess(this);
    }

    public void notifyLoadFailure() {
        onLoadFalure(this);
    }

}
