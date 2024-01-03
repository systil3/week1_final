package com.example.a1230;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ImageTag.class}, version = 1)
public abstract class ImageTagDB extends RoomDatabase {
    private static ImageTagDB instance = null;
    public abstract ImageTagDao imageTagDao();
    public static ImageTagDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            ImageTagDB.class, "departments.db")
                    .addMigrations(

                    )
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public static void destroyInstance() {
        instance = null;
    }
}