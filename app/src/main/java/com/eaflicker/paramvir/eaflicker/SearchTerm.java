package com.eaflicker.paramvir.eaflicker;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import org.jetbrains.annotations.NonNls;

@Entity
public class SearchTerm {
    @PrimaryKey
    @NonNull
    public String key;

    @Override
    public String toString() {
        return "SearchTerm{" +
                "key='" + key + '\'' +
                '}';
    }
}
