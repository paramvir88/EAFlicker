package com.eaflicker.paramvir.eaflicker.network;

import android.support.annotation.NonNull;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @NonNull
    @GET("services/rest?method=flickr.photos.search&api_key=40540e709e2f4935c9450089926e2ac6&per_page=10&format=json&extras=url_o&nojsoncallback=1")
    Call<FlickrResponse> searchPhotos(@Query("text") String text);
}
