package com.example.ahmed.freshpotato;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ahmed on 11/30/2016.
 */

public class PreferedDB extends SQLiteOpenHelper {
private static final String DBName="Movie.db";
    private static final int DBVersion=1;
    private static final String CreateQuery=
            "Create Table "+PreferedFilm.PrederdDetails.TABLENAME+" ("+PreferedFilm.PrederdDetails.POSTER+" TEXT,"+
            PreferedFilm.PrederdDetails.TITLE+" Text,"+PreferedFilm.PrederdDetails.PHOTO+" Text,"+PreferedFilm.PrederdDetails.NOBZA+" Text,"
                    +PreferedFilm.PrederdDetails.VOTEAVG+" Text,"+PreferedFilm.PrederdDetails.RELEASED+" Text,"+""+PreferedFilm.PrederdDetails.ID+" Text);";

    public PreferedDB(Context context){
super(context,DBName,null,DBVersion);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL(CreateQuery);
    }
public void ADD(Aflam film ,SQLiteDatabase db)
{

    ContentValues contentValues =new ContentValues();
    contentValues.put(PreferedFilm.PrederdDetails.POSTER,film.getPoster());
    contentValues.put(PreferedFilm.PrederdDetails.TITLE,film.getTilte());
    contentValues.put(PreferedFilm.PrederdDetails.PHOTO,film.getPhoto());
    contentValues.put(PreferedFilm.PrederdDetails.NOBZA,film.getNobza());
    contentValues.put(PreferedFilm.PrederdDetails.VOTEAVG,film.getVoteAvg());
    contentValues.put(PreferedFilm.PrederdDetails.RELEASED,film.getRelease_date());
    contentValues.put(PreferedFilm.PrederdDetails.ID,film.getId());
db.insert(PreferedFilm.PrederdDetails.TABLENAME,"Missing",contentValues);
}
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public Cursor GET(SQLiteDatabase db){
        Cursor cursor;

        String []Projections={PreferedFilm.PrederdDetails.POSTER,PreferedFilm.PrederdDetails.TITLE,
                PreferedFilm.PrederdDetails.PHOTO,PreferedFilm.PrederdDetails.NOBZA,
                PreferedFilm.PrederdDetails.VOTEAVG,PreferedFilm.PrederdDetails.RELEASED,PreferedFilm.PrederdDetails.ID};

        cursor=db.query(PreferedFilm.PrederdDetails.TABLENAME,Projections,null,null,null,null,null);
        //No need for selection or selection args, group rows,filter by row group or even sort order
        return cursor;
    }
}
