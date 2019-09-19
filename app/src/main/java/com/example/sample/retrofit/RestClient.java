package com.example.sample.retrofit;


import com.example.sample.model.Image;

import okhttp3.RequestBody;
import retrofit2.Callback;

public class RestClient {

    private static final String TAG = "RestClient";

    public static void commingImage(RequestBody user_id, RequestBody offset, RequestBody type, Callback<Image> callback) {
        RetrofitClient.getClient().imagecomming(user_id, offset, type).enqueue(callback);
    }


}