package com.withliyh.mylib.http;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2015/10/19.
 */
public class GsonRequest<T> extends JsonRequest<T> {

    private final Type type;
    private Gson gson;
    private Response.Listener<T> listener;


    public GsonRequest(final String url,
                           final Type type,
                           Response.Listener<T> listener,
                           Response.ErrorListener errorListener) {
        this(Method.GET, url, null, type, new Gson(), listener, errorListener);
    }

    public GsonRequest(final String url,
                           final String requestBody,
                           final Type type,
                           Response.Listener<T> listener,
                           Response.ErrorListener errorListener) {
        this(Method.POST, url, requestBody, type, new Gson(), listener, errorListener);
    }

    public GsonRequest(final int method,
                           final String url,
                           final String requestBody,
                           final Type type,
                           final Gson gson,
                           Response.Listener<T> listener,
                           Response.ErrorListener errorListener) {
        super(method, url, requestBody, listener, errorListener);

        this.gson = gson;
        this.type = type;
        this.listener = listener;
    }

    @Override
    protected void onFinish() {
        super.onFinish();
        this.listener = null;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return (Response<T>)Response.success(gson.fromJson(json, type), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        if (listener != null) { listener.onResponse(response);}
    }
}
