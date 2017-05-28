package com.example.ensai.beerchallenge;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import java.util.Iterator;
import java.util.Map;


/**
 * Created by ensai on 23/05/17.
 */

public class BieresConsommees extends Activity {

    ListView lv;
    private static Map<Integer, Integer> hmBieresConsommees;
    final ArrayList<Biere> listeBieres = new ArrayList<Biere>();


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.bieresconsommees);
        lv = (ListView) findViewById(R.id.listeBieresConsommees);

        /* On va chercher les bières consommées par l'utilisateur */
        SQLiteDatabase db = new BaseDeDonnees(this).getReadableDatabase();
        BiereDAO biereDAO = new BiereDAO(db);
        hmBieresConsommees = biereDAO.getBiereConsommees();

        /* On recupére les informations sur les bières qui sont stockées sur le portable */
        Iterator it = hmBieresConsommees.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Integer key = (Integer) entry.getKey();

            String myurl = "http://alban.guichard.free.fr/BeerChallenge/rechercheBiereParID.php?" + "id=" + key;

            OkHttpClient okhttpClient = new OkHttpClient();
            Request myGetRequest = new Request.Builder().url(myurl).build();

            okhttpClient.newCall(myGetRequest).enqueue(new Callback() {

                                                           public void onFailure(Request request, IOException e) {
                                                           }

                                                           public void onResponse(Response response) throws IOException {

                                                               try {
                                                                   String text = response.body().string();
                                                                   JSONArray json = new JSONArray(text);

                                                                   for (int i = 0; i < json.length(); i++) {

                                                                       JSONObject jsonobject = json.getJSONObject(i);
                                                                       Biere biere = new Biere();
                                                                       biere.setId(jsonobject.getInt("id_biere"));
                                                                       biere.setNom(jsonobject.getString("nom"));

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
                                                                       final ArrayAdapter<String> adapter = new ArrayAdapter<String>(BieresConsommees.this,
                                                                               android.R.layout.simple_list_item_1, listeNomDesBieres);
                                                                       lv.setAdapter(adapter);

                                                                       lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
                                                                       {
                                                                           @Override
                                                                           public void onItemClick(AdapterView<?> adapter, View v, int position,
                                                                                                   long arg3)
                                                                           {

                                                                               String value = (String)adapter.getItemAtPosition(position);

                                                                               for(Biere b: listeBieres){
                                                                                   if (b.getNom()==value){
                                                                                       Intent i1 = new Intent(v.getContext(), FicheBiere.class);
                                                                                       i1.putExtra("IDBiere", b.getId());
                                                                                       startActivityForResult(i1, 6);
                                                                                   };
                                                                               }
                                                                           }
                                                                       });

                                                                   }
                                                               }); // fin runOnUiThread
                                                           }
                                                       }
            );
        }
    }

}
