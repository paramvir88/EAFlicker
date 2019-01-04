package com.eaflicker.paramvir.eaflicker.network;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.eaflicker.paramvir.eaflicker.Photo;
import com.eaflicker.paramvir.eaflicker.database.FlickrDatabase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class FlickrService {

    FlickrDatabase database;


    void init(Context context) {
         database = Room.databaseBuilder(context.getApplicationContext(), FlickrDatabase.class, "fl_databse").build();
    }

    public void searchPhotos(String searchQuery, final GenericApiListener<List<Photo>, String> listener) {


        ApiClient.getInterface().searchPhotos(searchQuery).enqueue(new Callback<FlickrResponse>() {
            @Override
            public void onResponse(Call<FlickrResponse> call, Response<FlickrResponse> response) {
                database.photoDao().insertAll(response.body().photos.photo);
                listener.onSucsess(response.body().photos.photo);
            }

            @Override
            public void onFailure(Call<FlickrResponse> call, Throwable t) {
                listener.onFailure("");
            }
        });
    }
}
