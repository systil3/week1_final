package com.example.a1230;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Map;

@Entity(
        /*foreignKeys = @ForeignKey(
                entity = Department.class,
                parentColumns = "dept_id",
                childColumns = "dept_id",
                onDelete = ForeignKey.CASCADE // Optional: Define the ON DELETE action
        )*/
)
public class Person {
    @PrimaryKey(autoGenerate = true)
    public Integer id;

    public String name;
    public Integer gender;
    public String phonenum;

    public Integer dept_id;
    public String image_path;

    public Person() {
    }
    public Person(Integer id, String name, Integer gender, String phonenum, Integer dept) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.phonenum = phonenum;
        this.dept_id = dept;
    }
    public Integer getId() {return this.id;}
    public String getInfo() {
        return this.phonenum;
    }
    public String getName() {return this.name; }
    public String getGender() {return this.gender == 1 ? "Male" : "Female"; }
    public String getPhone() {return this.phonenum; }
    public Integer getDeptNum() {
        return this.dept_id;
    }
    public String getDept() {
        Map<Integer, String> deptMap = Map.of(
                0, "Management",
                1, "General Affairs",
                2, "Accounting",
                3, "Machinary",
                4, "Technical Research",
                5, "Human Resources",
                6, "System Management",
                7, "Facility Management");
        return deptMap.get(this.dept_id);
    }
}