package com.example.ensai.beerchallenge;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ensai on 27/05/17.
 */

public class BaseDeDonnees extends SQLiteOpenHelper {


        private static int VERSION = 2 ;
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
            db.execSQL("INSERT INTO  biere (idbiere,note) values (1,5)");
            db.execSQL("INSERT INTO  biere (idbiere,note) values (2,10)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            if(oldVersion==1){
                db.execSQL("INSERT INTO  biere (idbiere,note) values (3,10)");
                db.execSQL("INSERT INTO  biere (idbiere,note) values (4,10)");
                VERSION = 2;
            }



        }


    }

