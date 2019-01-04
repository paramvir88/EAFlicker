package com.eaflicker.paramvir.eaflicker.database;

import android.arch.persistence.room.*;
import com.eaflicker.paramvir.eaflicker.Photo;

import java.util.List;

@Dao
public interface PhotoDao {

    @Query("SELECT * FROM Photo")
    List<Photo> getAllPhotos();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Photo> photos);

    @Delete
    void delete(Photo photo);

    @Query("SELECT * FROM Photo WHERE searchKey=:key")
    List<Photo> getSearchedPhotos(final String key);
}
