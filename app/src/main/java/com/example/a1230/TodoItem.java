package com.example.a1230;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

// TodoItem.java
@Entity(
        /*foreignKeys = @ForeignKey(
                entity = Department.class,
                parentColumns = "dept_id",
                childColumns = "dept_id",
                onDelete = ForeignKey.CASCADE // Optional: Define the ON DELETE action
        )*/
)
public class TodoItem {
    @PrimaryKey(autoGenerate = true)
    public Integer todo_id;
    public Integer dept_id;
    public String task;
    public boolean completed;

    public TodoItem(Integer dept_id, String task, Boolean completed) {
        this.dept_id = dept_id;
        this.task = task;
        this.completed = completed;
    }
    public Integer getId() {return this.todo_id;}
    public Integer getDept_id() {return this.dept_id;}
    public String getTask() {
        return this.task;
    }

    public String setTask(String task) {
        this.task = task;
        return task;
    }

    public void setAsCompleted() {this.completed = true; }
    public void setAsUncompleted() {this.completed = false;}
    public Boolean getCompleted() {
        return completed;
    }
}