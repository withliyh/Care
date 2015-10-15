package com.withliyh.mylib.viewholder;

import android.view.View;

/**
 * Created by Administrator on 2015/10/15.
 */
public interface ViewHolder {
    int FIRST_TYPE_ITEM = 0;
    <T extends View> T getView(int viewId);
    View getConvertView();
}
