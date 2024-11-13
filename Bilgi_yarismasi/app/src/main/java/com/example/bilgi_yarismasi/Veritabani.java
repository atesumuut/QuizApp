package com.example.bilgi_yarismasi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public abstract class Veritabani extends SQLiteOpenHelper {
    private static final String Veritabani_adi = "Giris.db";
    private static final int SURUM = 1;

    public Veritabani (Context context){
        super (context, "Giris.db",null, SURUM);
    }

    @Override
    public void onCreate(SQLiteDatabase Mydb) {
        Mydb.execSQL("create table users (username TEXT primary key, skor TEXT, Sorular TEXT) ");
    }


    public Boolean insterData(String username){
        SQLiteDatabase Mydb = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        long result = Mydb.insert("users", null, contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase Mydb = this.getWritableDatabase();
        Cursor cursor = Mydb.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}
