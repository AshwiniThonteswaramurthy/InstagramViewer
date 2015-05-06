package com.android.ashwini.instagramviewer;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class InstagramClient {

    private static final String INSTAGRAM_CLIENT_ID = "8996167c3f5a462da2067ef8663df3ad";
    private static final String INSTAGRAM_POPULAR_MEDIA_URL = "https://api.instagram.com/v1/media/popular";

    private static AsyncHttpClient httpClient;

    public InstagramClient() {
        this.httpClient = new AsyncHttpClient();
    }


    public void getInstagramPhotoViewer(JsonHttpResponseHandler responseHandler) {
        RequestParams requestParams = new RequestParams("client_id", INSTAGRAM_CLIENT_ID);
        httpClient.get(INSTAGRAM_POPULAR_MEDIA_URL, requestParams, responseHandler);
    }
}
