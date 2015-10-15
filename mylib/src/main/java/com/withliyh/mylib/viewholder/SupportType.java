package com.withliyh.mylib.viewholder;

/**
 * Created by Administrator on 2015/10/14.
 */
public interface SupportType<T> {
    int getLayoutId(int position, T t);

    int getViewTypeCount();

    /**
     *  类型0为保留项，用作加载更多
     * @param postion
     * @param t
     * @return
     */
    int getItemViewType(int postion, T t);
}
