package com.example.a1230;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ImageTagDao {
    @Query("SELECT * FROM ImageTag")
    List<ImageTag> getAll();

    @Query("SELECT * FROM ImageTag WHERE ImageTag.tag_id = (:tag_id)")
    List<ImageTag> loadWithTagId(int tag_id);

    @Insert
    void insertAll(ImageTag... imageTags);

    @Update
    void update(ImageTag imageTag);

    @Delete
    void delete(ImageTag imageTag);

    @Query("DELETE FROM ImageTag")
    void deleteAll();

}
