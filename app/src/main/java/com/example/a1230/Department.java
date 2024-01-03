package com.example.a1230;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Department {
    @PrimaryKey(autoGenerate = false)
    public Integer dept_id;

    public String name;

    public Department(Integer dept_id, String name) {
        this.dept_id = dept_id;
        this.name = name;
    }
    public Integer getId() {return this.dept_id;}
    public String getName() {return this.name; }

}