package com.app.ensai.beerchallenge;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by ensai on 23/05/17.
 */

public class ToutesLesBieres extends Activity {

    ListView lv ;
    private static ArrayList<Biere> listeDesBieres;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.touteslesbieres);
        lv = (ListView) findViewById(R.id.listeToutesLesBieres);

        String myurl = "http://alban.guichard.free.fr//BeerChallenge/rechercheBiere.php";

        OkHttpClient okhttpClient = new OkHttpClient();
        Request myGetRequest = new Request.Builder().url(myurl).build();

        okhttpClient.newCall(myGetRequest).enqueue(new Callback() {

                                                       public void onFailure(Request request, IOException e) {
                                                       }

                                                       public void onResponse(Response response) throws IOException {
                                                           final ArrayList<Biere> listeBieres=new ArrayList<Biere>();

                                                           try {
                                                               String text = response.body().string();
                                                               JSONArray json = new JSONArray(text);

                                                               for (int i = 0; i < json.length(); i++) {
                                                                   JSONObject jsonobject = json.getJSONObject(i);
                                                                   Biere biere = new Biere();
                                                                   biere.setId(jsonobject.getInt("id_biere"));
                                                                   biere.setNom(jsonobject.getString("nom"));
                                                                   biere.setBrasserie(jsonobject.getString("nom_brasserie"));
                                                                   biere.setCouleur(jsonobject.getString("libelle_couleur"));
                                                                   biere.setDegre_alcool(jsonobject.getDouble("degre_alcool"));
                                                                   biere.setFermentation(jsonobject.getString("libelle_fermentation"));
                                                                   biere.setType(jsonobject.getString("libelle_type"));

                                                                   listeBieres.add(biere);
                                                               }


                                                           } catch (JSONException exc) {

                                                               exc.printStackTrace();
                                                           }

                                                           runOnUiThread(new Runnable() {
                                                               @Override
                                                               public void run() {
                                                                   ArrayList<String> listeNomDesBieres= new ArrayList<String>();
                                                                   for(Biere b: listeBieres){
                                                                       listeNomDesBieres.add(b.getNom());
                                                                   }
                                                                   final ArrayAdapter<String> adapter = new ArrayAdapter<String>(ToutesLesBieres.this,
                                                                           android.R.layout.simple_list_item_1, listeNomDesBieres);
                                                                   lv.setAdapter(adapter);
                                                               }
                                                           }); // fin runOnUiThread
                                                           listeDesBieres=listeBieres;
                                                       }
                                                   }
        );
    }
}
