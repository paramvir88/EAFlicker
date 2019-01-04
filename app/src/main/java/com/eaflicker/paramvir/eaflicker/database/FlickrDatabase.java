package com.eaflicker.paramvir.eaflicker.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.eaflicker.paramvir.eaflicker.Photo;

@Database(entities = {Photo.class}, version = 1)
public abstract class FlickrDatabase extends RoomDatabase {

    public abstract  PhotoDao photoDao();


}
