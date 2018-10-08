package com.develop.mita.ddimitrijevic.topratedmovie_quantox.Api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.develop.mita.ddimitrijevic.topratedmovie_quantox.Api.ApiConstants.BASE_URL_ADDRESS_VER_3;

public class ApiClient {
    public static Retrofit getClient() {
        Retrofit retrofit = null;

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> chain.proceed(chain.request()
                        .newBuilder()
                        .build()))
                .readTimeout(50, TimeUnit.SECONDS)
                .connectTimeout(50, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_ADDRESS_VER_3)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }
}
