package com.nd.myself.viewholder;

/**
 * Created by Administrator on 2015/10/14.
 */
public interface TypeSupport<T> {
    int getLayoutId(int position, T t);

    int getViewTypeCount();

    int getItemViewType(int postion, T t);
}
