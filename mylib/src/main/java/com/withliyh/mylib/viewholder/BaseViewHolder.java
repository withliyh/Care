package com.withliyh.mylib.viewholder;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2015/10/13.
 */
public class BaseViewHolder {

    public final static int FIRST_TYPE_ITEM = 0;
    protected final SparseArray<View> mViews;
    protected int mLayoutId;
    protected View mConvertView;
    private Context mContext;

    public BaseViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mViews = new SparseArray<>();
        this.mLayoutId = layoutId;
        this.mContext = context;
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    public BaseViewHolder() {
        this.mViews = new SparseArray<>();
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }

    public int getLayoutId() {
        return mLayoutId;
    }


    public BaseViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    public BaseViewHolder setTextColor(int viewId, int color) {
        TextView view = getView(viewId);
        view.setTextColor(color);
        return this;
    }

    public BaseViewHolder setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    public BaseViewHolder setImageUrl(int viewId, String url) {
        ImageView view = getView(viewId);
        Glide.with(mContext).load(url).into(view);
        return this;
    }
}
