package com.example.multiscreen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DBHandler extends SQLiteOpenHelper {
    public DBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Bitmap getBitmap(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Bitmap bt = null;
        Cursor cursor = db.rawQuery("SELECT * FROM ImageTable WHERE id = ?" ,new String[]{String.valueOf(id)});
        if (cursor.moveToNext()) {
            byte[] blob = cursor.getBlob(1);
            bt = BitmapFactory.decodeByteArray(blob, 0, blob.length);
        }
        return bt;
    }

    public Boolean insertimage(String x, Integer i ) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            FileInputStream fs = new FileInputStream(x);
            byte[] image = new byte[fs.available()];
            fs.read(image);
            ContentValues contentValues = new ContentValues();
            contentValues.put("image", image);
            db.insert("ImageTable", null, contentValues);
            fs.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
