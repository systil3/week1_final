package com.example.a1230;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Image.class}, version = 4)
public abstract class ImageDB extends RoomDatabase {
    private static ImageDB instance = null;
    public abstract ImageDao imageDao();

    public static ImageDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            ImageDB.class, "departments.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public static void destroyInstance() {
        instance = null;
    }
}