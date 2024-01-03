package com.example.a1230;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DeptDao {
    @Query("SELECT * FROM Department")
    List<Department> getAll();

    @Query("SELECT * FROM Department WHERE dept_id IN (:deptIds)")
    List<Department> loadAllByIds(int[] deptIds);

    @Insert
    void insertAll(Department... depts);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Department dept);
    @Update
    void update(Department dept);

    @Delete
    void delete(Department dept);

}
