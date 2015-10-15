package com.withliyh.mylib.viewholder;

import android.content.Context;
import android.view.View;

/**
 * Created by Administrator on 2015/10/15.
 */
public interface SupportLoad {

    View getView(Context context, View parent);
    void onLoading(BaseViewHolder helper);
    void onLoadSuccess(BaseViewHolder helper);
    void onLoadFalure(BaseViewHolder helper);
}
