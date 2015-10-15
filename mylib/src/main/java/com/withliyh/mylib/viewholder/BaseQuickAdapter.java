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
    protected SupportType<T> mTypeSupport; //多类型支持

    protected boolean mEnableLoadMore;     //是否开启加载更多



    public BaseQuickAdapter(Context context, int layoutId, List<T> datas) {
        this.mContext = context;
        this.mLayoutId = layoutId;
        this.mDatas = datas;
    }

    public BaseQuickAdapter(Context context, SupportType<T> typeSupport, List<T> datas) {
        this.mContext = context;
        this.mTypeSupport = typeSupport;
        this.mDatas = datas;
    }

    @Override
    public int getItemViewType(int position) {
        if (mEnableLoadMore) {
            if (position == mDatas.size() -1) {
                return ViewHolder.FIRST_TYPE_ITEM;
            }
        }
        if (mTypeSupport != null) {
            return mTypeSupport.getItemViewType(position, mDatas.get(position));
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        int typeCount = super.getViewTypeCount();
        if (mTypeSupport != null) {
            //如果 类型数位 2，  getItemViewType 的有效值只能是 0、1
            typeCount = mTypeSupport.getViewTypeCount();
        }
        if (mEnableLoadMore) {
            typeCount = typeCount + 1;
        }
        return typeCount;
    }

    @Override
    public int getCount() {
        if (mEnableLoadMore) {
            return mDatas.size() + 1;
        }
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
        final ViewHolder viewHolder = getViewHolder(position, convertView, parent);
        convert(viewHolder, getItem(position));
        return viewHolder.getConvertView();
    }

    public abstract ViewHolder getViewHolder(int position, View convertView, ViewGroup parent);

    public abstract void convert(ViewHolder helper, T item);
}
