package com.example.sample.retrofit;




import com.example.sample.model.Image;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {


    @Multipart
    @POST("xttest/getdata.php")
    Call<Image> imagecomming(@Part("user_id") RequestBody user_id,
                             @Part("offset") RequestBody offset,
                             @Part("type") RequestBody type);



}