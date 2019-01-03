package com.eaflicker.paramvir.eaflicker.network;

import android.support.annotation.Nullable;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


class ApiClient {

    @Nullable
    private static Retrofit sRetrofit = null;
    @Nullable
    private static ApiInterface sInterface = null;

    @Nullable
    static Retrofit getClient() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl("https://api.flickr.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }

    @Nullable
    static ApiInterface getInterface() {
        if (sInterface == null) {
            sInterface = getClient().create(ApiInterface.class);
        }
        return sInterface;
    }
}
