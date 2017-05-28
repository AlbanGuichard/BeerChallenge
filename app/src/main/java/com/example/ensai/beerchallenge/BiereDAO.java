package com.example.ensai.beerchallenge;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ensai on 27/05/17.
 */

public class BiereDAO {

    private static Map<Integer, Integer> hm = new HashMap<>();

    public BiereDAO(SQLiteDatabase db){
        Cursor cursor = db.rawQuery("SELECT idbiere, note FROM biere", null);

        while(cursor.moveToNext()){
            hm.put(cursor.getInt(0),cursor.getInt(1));
        }
        db.close();

    }

    public Map<Integer, Integer> getBiereConsommees(){
        return hm ;
    }

    public int getVote(int id_biere){
        return hm.get(id_biere);
    }

    public void setVote(SQLiteDatabase db, int id_biere, int note){
        hm.put(id_biere,note);
        db.rawQuery("UPDATE biere SET note =? WHERE idbiere=?", new String [] {String.valueOf(note),String.valueOf(id_biere)});
        db.close();
    }

    public void ajouterNouvelleBiere(SQLiteDatabase db, int id_biere){
        hm.put(id_biere,null);
        db.rawQuery("INSERT INTO biere (idbiere) VALUES ?", new String [] {String.valueOf(id_biere)});
        db.close();
    }

}
