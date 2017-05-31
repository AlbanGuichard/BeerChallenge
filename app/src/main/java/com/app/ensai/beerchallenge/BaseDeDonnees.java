package com.app.ensai.beerchallenge;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ensai on 27/05/17.
 */

public class BaseDeDonnees extends SQLiteOpenHelper {


        private static int VERSION = 10 ;
        private static final String NAME = "BeerChallenge" ;

        public BaseDeDonnees(Context context) {
            super(context, NAME, null, VERSION);
        }

        public BaseDeDonnees(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
            super(context, name, factory, version, errorHandler);
        }

        public static int getVERSION() {
            return VERSION;
        }

        public static String getNAME() {
            return NAME;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE biere (idbiere INTEGER, note INTEGER)");
            db.execSQL("CREATE TABLE challenge (ch VARCHAR(100), compteur INTEGER)");
            db.execSQL("INSERT INTO challenge (ch,compteur) VALUES ('ambree',0),('blanche',0),('blonde',0),('brune',0),('double',0),('triple',0),('total',0)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            if(oldVersion<10){
                db.execSQL("DROP TABLE challeng");
                db.execSQL("CREATE TABLE biere (idbiere INTEGER, note INTEGER)");
                db.execSQL("CREATE TABLE challenge (ch VARCHAR(100), compteur INTEGER)");
                db.execSQL("INSERT INTO challenge (ch,compteur) VALUES ('ambree',0),('blanche',0),('blonde',0),('brune',0),('double',0),('triple',0),('total',0)");
                VERSION=10;
            }


        }


    }

