package com.withliyh.mylib.viewholder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2015/10/13.
 */
public abstract class BaseQuickAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected List<T> mDatas;
    protected int mLayoutId;
    protected TypeSupport<T> mTypeSupport;

    public BaseQuickAdapter(Context context, int layoutId, List<T> datas) {
        this.mContext = context;
        this.mLayoutId = layoutId;
        this.mDatas = datas;
    }

    public BaseQuickAdapter(Context context, TypeSupport<T> typeSupport, List<T> datas) {
        this.mContext = context;
        this.mTypeSupport = typeSupport;
        this.mDatas = datas;
    }

    @Override
    public int getItemViewType(int position) {
        if (mTypeSupport != null) {
            return mTypeSupport.getItemViewType(position, mDatas.get(position));
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        if (mTypeSupport != null) {
            //因为类型从0开始，设为1标识有2中类型
            return mTypeSupport.getViewTypeCount() - 1;
        }
        return super.getViewTypeCount();
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final BaseViewHolder viewHolder = getViewHolder(position, convertView, parent);
        convert(viewHolder, getItem(position));
        return viewHolder.getConvertView();
    }

    public abstract BaseViewHolder getViewHolder(int position, View convertView, ViewGroup parent);

    public abstract void convert(BaseViewHolder helper, T item);
}
