package com.example.a1230;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Image {
    @PrimaryKey(autoGenerate = true)
    public Integer img_id;
    @NonNull
    public String uri;
    public String name;
    public Integer dept_id;

    public Image(String uri, String name, Integer dept_id) {
        this.uri = uri;
        this.name = name;
        this.dept_id = dept_id;
    }
    public Integer getId() {return this.dept_id;}
    public String getName() {return this.name; }
    public String getUri() {return this.uri; }

}