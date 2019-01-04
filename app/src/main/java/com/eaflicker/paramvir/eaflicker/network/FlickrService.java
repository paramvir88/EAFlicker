package com.eaflicker.paramvir.eaflicker.network;

import com.eaflicker.paramvir.eaflicker.Photo;
import com.eaflicker.paramvir.eaflicker.database.FlickrDatabase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class FlickrService {

    FlickrDatabase database;

    public FlickrService(FlickrDatabase db) {
        database = db;
    }


    public void searchPhotos(final String searchQuery, final GenericApiListener<List<Photo>, String> listener) {


        ApiClient.getInterface().searchPhotos(searchQuery).enqueue(new Callback<FlickrResponse>() {
            @Override
            public void onResponse(Call<FlickrResponse> call, final Response<FlickrResponse> response) {
                new Thread(() -> {
                    response.body().photos.photo.forEach(it -> it.setSearchKey(searchQuery));
                    database.photoDao().insertAll(response.body().photos.photo);
                }).start();

                listener.onSucsess(response.body().photos.photo);
            }

            @Override
            public void onFailure(Call<FlickrResponse> call, Throwable t) {
                //listener.onFailure("");
                new Thread(() -> {
                    List<Photo> photos = database.photoDao().getSearchedPhotos(searchQuery);
                    listener.onSucsess(photos);
                }).start();

            }
        });
    }
}
