package com.eaflicker.paramvir.eaflicker.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.eaflicker.paramvir.eaflicker.Photo;
import com.eaflicker.paramvir.eaflicker.SearchTerm;

import java.util.List;
@Dao
public interface SearchTermDao {

    @Query("SELECT * FROM SearchTerm")
    List<SearchTerm> getAllSearchTerms();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(SearchTerm term);
}
