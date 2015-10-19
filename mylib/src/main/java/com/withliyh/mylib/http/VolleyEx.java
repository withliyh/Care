package com.withliyh.mylib.http;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.squareup.okhttp.OkHttpClient;

/**
 * Created by Administrator on 2015/10/19.
 */
public class VolleyEx {
    private static VolleyEx sInstance;
    private Context mContext;
    private RequestQueue mRequestQueue;

    private VolleyEx() {
    }

    public void init(Context ctx) {
        mContext = ctx;
    }

    public static VolleyEx getInstance() {
        if (sInstance == null) {
            sInstance = new VolleyEx();
        }
        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext(), new OkHttpStack(new OkHttpClient()));
        }
        return mRequestQueue;
    }

    public <T> void addRequest(Request<T> request) {
        getRequestQueue().add(request);
    }

    public <T> void addRequest(Request<T> request, Object tag) {
        request.setTag(tag);
        getRequestQueue().add(request);
    }

    public void cancelAll(Object tag) {
        getRequestQueue().cancelAll(tag);
    }


}
