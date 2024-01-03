package com.example.a1230;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ImageDao {
    @Query("SELECT * FROM Image")
    List<Image> getAll();

    @Query("SELECT * FROM Image WHERE img_id IN (:imgIds)")
    List<Image> loadAllByIds(int[] imgIds);

    @Query("SELECT * FROM Image " +
            "WHERE Image.dept_id == (:deptId)")
    List<Image> selectAllImagesInDept(int deptId);
    @Insert
    void insertAll(Image... imgs);

    @Update
    void update(Image img);

    @Delete
    void delete(Image img);

    @Query("DELETE FROM Image")
    void deleteAll();

}
