package com.example.a1230;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

@Entity
public class ImageTag {
    @PrimaryKey
    public Integer tag_id;
    public String tag;
    public ImageTag(Integer tag_id, String tag) {
        this.tag_id = tag_id;
    }
}