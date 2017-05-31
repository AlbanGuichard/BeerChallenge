package com.app.ensai.beerchallenge;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ensai on 27/05/17.
 */

public class ChallengeDAO {

    private static Map<String, Integer> hm = new HashMap<>();
    public static SQLiteDatabase db ;

    public ChallengeDAO(Context context){
        db = new BaseDeDonnees(context).getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT ch, compteur FROM challenge", null);

        while(cursor.moveToNext()){
            hm.put(cursor.getString(0),cursor.getInt(1));
        }
    }

    public Map<String, Integer> getChallenge(){
        return hm ;
    }

    public int getCompteur(String challenge){
        return hm.get(challenge);
    }

    public void setCompteur(String challenge){
        int ancienCompteur = getCompteur(challenge);;
        int nouveauCompteur = ancienCompteur + 1;;
        hm.put(challenge,nouveauCompteur);
        db.execSQL("UPDATE challenge SET compteur =(?) WHERE ch=(?)", new String [] {String.valueOf(nouveauCompteur),String.valueOf(challenge)});
    }
    
    public void closeDB(){
        db.close();
    }

}
