package com.nd.care;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.nd.care.bean.MeiZiTu;
import com.withliyh.mylib.http.GsonRequest;

/**
 * Created by Administrator on 2015/10/19.
 */
public class ApiRequest {
    private final static String url = "http://api.lovebizhi.com/macos_v4.php?a=category&spdy=1&tid=3&order=hot&color_id=3&device=105&uuid=436e4ddc389027ba3aef863a27f6e6f9&mode=0&retina=0&client_id=1008&device_id=31547324&model_id=105&size_id=0&channel_id=70001&screen_width=1920&screen_height=1200&bizhi_width=1920&bizhi_height=1200&version_code=19&language=zh-Hans&jailbreak=0&mac=&p=1";
    private static final Gson gson = new Gson();

    public static GsonRequest<MeiZiTu> getMeiZiTuRequest(
            final Response.Listener<MeiZiTu> listener,
            final Response.ErrorListener errorListener) {
        return new GsonRequest<>(url, MeiZiTu.class, listener, errorListener);
    }

}
