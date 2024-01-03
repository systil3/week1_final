package com.example.a1230;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface PersonDao {
    @Query("SELECT * FROM Person")
    List<Person> getAll();

    @Query("SELECT * FROM Person WHERE id IN (:personIds)")
    List<Person> loadAllByIds(int[] personIds);

    @Query("SELECT * FROM Person " +
            "WHERE Person.dept_id == (:deptId)")
    List<Person> selectAllPersonsInDept(int deptId);

    @Query("WITH search_res1 AS (" +
            "SELECT * FROM Person WHERE name LIKE :text || '%' ORDER BY name)," +
            "search_res2 AS (SELECT * FROM Person WHERE name LIKE '%' || :text || '%' ORDER BY name)" +
            "SELECT DISTINCT * FROM (SELECT * FROM search_res1 UNION ALL SELECT * FROM search_res2)")
    List<Person> searchNameByText(String text);
    @Insert
    void insertAll(ArrayList<Person> persons);

    @Update
    void update(Person person);

    @Delete
    void delete(Person person);

    @Query("DELETE FROM Person WHERE Person.id = (:id)")
    void deleteById(Integer id);

    @Query("DELETE FROM Person")
    void deleteAll();
}
