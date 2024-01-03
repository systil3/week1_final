package com.example.a1230;

import android.content.Context;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TodoItem.class, Department.class}, version = 3)
public abstract class TodoItemDB extends RoomDatabase {
    private static TodoItemDB instance = null;

    public abstract TodoItemDao todoItemDao();

    public static TodoItemDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            TodoItemDB.class, "departments.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        };
        return instance;
    }

    //public static createOpenHelper() {
    //}
    public static void destroyInstance() {
        instance = null;
    }
}

/*.addMigrations(new Migration(5, 6) {
@Override
public void migrate(@NonNull SupportSQLiteDatabase database) {
        database.execSQL("DROP TABLE IF EXISTS TodoItem; \n");
        database.execSQL(
        "CREATE TABLE \"TodoItem\" (\n" +
        "\t\"todo_id\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
        "\t\"dept_id\"\tINTEGER,\n" +
        "\t\"task\"\tTEXT,\n" +
        "\t\"completed\"\tINTEGER,\n" +
        "\tFOREIGN KEY(\"dept_id\") REFERENCES Departments(\"dept_id\") ON DELETE CASCADE ON UPDATE CASCADE\n" +
        ");");
        }
        }). */