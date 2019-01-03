package com.eaflicker.paramvir.eaflicker.network;

import com.eaflicker.paramvir.eaflicker.Photo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class FlickrService {

    public void searchPhotos(String searchQuery, final GenericApiListener<List<Photo>, String> listener){


        ApiClient.getInterface().searchPhotos(searchQuery).enqueue(new Callback<FlickrResponse>() {
            @Override
            public void onResponse(Call<FlickrResponse> call, Response<FlickrResponse> response) {
                listener.onSucsess(response.body().photos.photo);
            }

            @Override
            public void onFailure(Call<FlickrResponse> call, Throwable t) {
                listener.onFailure("");
            }
        });
    }
}
