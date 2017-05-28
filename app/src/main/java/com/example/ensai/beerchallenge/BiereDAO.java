package com.example.ensai.beerchallenge;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ensai on 27/05/17.
 */

public class BiereDAO {

    private static Map<Integer, Integer> hm = new HashMap<>();
    public static SQLiteDatabase db ;

    public BiereDAO(Context context){
        db = new BaseDeDonnees(context).getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT idbiere, note FROM biere", null);

        while(cursor.moveToNext()){
            hm.put(cursor.getInt(0),cursor.getInt(1));
        }
    }

    public Map<Integer, Integer> getBiereConsommees(){
        return hm ;
    }

    public int getVote(int id_biere){
        return hm.get(id_biere);
    }

    public void setVote(int id_biere, int note){
        hm.put(id_biere,note);
        Log.i("AA","Je suis passé par là :)");
        db.execSQL("UPDATE biere SET note =(?) WHERE idbiere=(?)", new String [] {String.valueOf(note),String.valueOf(id_biere)});
    }

    public void ajouterNouvelleBiere(int id_biere){
        hm.put(id_biere,null);
        db.execSQL("INSERT INTO biere (idbiere) VALUES (?)", new String [] {""+id_biere});
    }

    public void closeDB(){
        db.close();
    }

}
