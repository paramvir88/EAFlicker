package com.eaflicker.paramvir.eaflicker.network;

import android.arch.persistence.room.Room;
import android.content.Context;
import com.eaflicker.paramvir.eaflicker.database.FlickrDatabase;

public class SearchService {


    FlickrDatabase database;

    public SearchService(Context context) {
        init(context);
    }

    void init(Context context) {
        database = Room.databaseBuilder(context.getApplicationContext(), FlickrDatabase.class, "fl_databse").build();
    }
}
