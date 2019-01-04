package com.eaflicker.paramvir.eaflicker.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.eaflicker.paramvir.eaflicker.Photo;

import java.util.List;

@Dao
public interface PhotoDao {

    @Query("SELECT * FROM photo")
    List<Photo> getAllPhotos();

    @Insert
    void insertAll(List<Photo> photos);

    @Delete
    void delete(Photo photo);
}
