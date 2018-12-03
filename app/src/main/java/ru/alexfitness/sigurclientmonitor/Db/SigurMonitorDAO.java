package ru.alexfitness.sigurclientmonitor.Db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SigurMonitorDAO {

    public static String getName(Context context, int id){
        String result = null;
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            SigurDbHelper dbHelper = new SigurDbHelper(context);
            db = dbHelper.getReadableDatabase();
            cursor = db.rawQuery("SELECT NAME, FATHERNAME FROM VISITORS WHERE SIGUR_ID = ?", new String[]{String.valueOf(id)});
            if (cursor.moveToNext()) {
                StringBuilder sb = new StringBuilder();
                sb.append(cursor.getString(0)).append(" ").append(cursor.getString(1));
                result = sb.toString();
            }
        } catch(Exception ex){
            ex.printStackTrace();
        } finally {
            if(cursor!=null) {
                cursor.close();
            }
            if(db!=null){
                db.close();
            }
        }
        return result;
    }

    public static Bitmap getPhotoData(Context context, int id) throws FileNotFoundException {
        FileInputStream fis = context.openFileInput(String.format("%d", id));
        Bitmap bm = BitmapFactory.decodeStream(fis);
        return bm;
    }

}
