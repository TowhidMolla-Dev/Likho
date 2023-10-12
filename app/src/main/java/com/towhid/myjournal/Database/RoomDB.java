package com.towhid.myjournal.Database;

import android.content.Context;

import androidx.room.Database;

import com.towhid.myjournal.Models.Notes;

@Database(entities = Notes.class, version = 1, exportSchema = false)
public abstract class RoomDB {
    private static RoomDB database;
    private static String DATABASE_NAME = "NoteAPP";

    public synchronized static RoomDB getInstance(Context context){

    }
}
