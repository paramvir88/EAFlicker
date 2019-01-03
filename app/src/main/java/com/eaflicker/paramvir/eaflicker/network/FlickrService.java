package com.eaflicker.paramvir.eaflicker.network;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlickrService {

    public void searchPhotos(String searchQuery){
        ApiClient.getInterface().searchPhotos(searchQuery).enqueue(new Callback<FlickrResponse>() {
            @Override
            public void onResponse(Call<FlickrResponse> call, Response<FlickrResponse> response) {

            }

            @Override
            public void onFailure(Call<FlickrResponse> call, Throwable t) {

            }
        });
    }
}
