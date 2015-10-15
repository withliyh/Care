package com.withliyh.mylib.viewholder;

import android.content.Context;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2015/10/15.
 */
public interface SupportLoad {

    BaseViewHolder createHolder(Context context, ViewGroup parent);
    void onLoading(BaseViewHolder helper);
    void onLoadSuccess(BaseViewHolder helper);

    void onLoadFailure(BaseViewHolder helper);
}
