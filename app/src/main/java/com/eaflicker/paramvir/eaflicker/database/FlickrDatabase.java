package com.eaflicker.paramvir.eaflicker.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.eaflicker.paramvir.eaflicker.Photo;
import com.eaflicker.paramvir.eaflicker.SearchTerm;

@Database(entities = {Photo.class, SearchTerm.class}, version = 3)
public abstract class FlickrDatabase extends RoomDatabase {

    public abstract PhotoDao photoDao();

    public abstract SearchTermDao searchTermDao();


}
