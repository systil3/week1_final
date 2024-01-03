package com.example.a1230;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TodoItemDao {

    @Query("SELECT * FROM TodoItem")
    List<TodoItem> getAll();

    @Query("SELECT * FROM TodoItem WHERE todo_id IN (:todoIds)")
    List<TodoItem> loadAllByIds(int[] todoIds);

    @Query("SELECT * FROM TodoItem " +
            "WHERE TodoItem.dept_id == (:deptId)")
    List<TodoItem> selectAllTodoItemsInDept(int deptId);

    @Query("SELECT * FROM TodoItem " +
            "WHERE NOT completed")
    List<TodoItem> selectAllUncompleted();
    @Insert
    void insertAll(TodoItem... depts);

    @Update
    void update(TodoItem todoItem);

    @Update
    void update(ArrayList<TodoItem> todoItem);

    @Delete
    void delete(TodoItem dept);

    @Query("DELETE FROM TodoItem WHERE TodoItem.todo_id = (:todo_Id)")
    void deleteById(Integer todo_Id);

    @Query("DELETE FROM TodoItem")
    void deleteAll();
}
