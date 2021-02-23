package fr.eni.courses.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import fr.eni.courses.dao.contracts.ArticleContract;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "AndroKado.db";


    public DatabaseHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(ArticleContract.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL(ArticleContract.DROP_TABLE);
        onCreate(sqLiteDatabase);
    }
}
