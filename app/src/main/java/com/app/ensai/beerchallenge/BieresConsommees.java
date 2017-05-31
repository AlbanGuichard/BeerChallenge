package com.app.ensai.beerchallenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

import static com.app.ensai.beerchallenge.R.id.textView;


/**
 * Created by ensai on 23/05/17.
 */

public class BieresConsommees extends Activity {

    static Integer key = null;
    static Integer note = null;
    private static Map<Integer, Integer> hmBieresConsommees;
    final ArrayList<Biere> listeBieres = new ArrayList<Biere>();
    ListView lv;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.bieresconsommees);
        lv = (ListView) findViewById(R.id.listeBieresConsommees);


/* On va chercher les bières consommées par l'utilisateur */
        BiereDAO biereDAO = new BiereDAO(BieresConsommees.this);
        hmBieresConsommees = biereDAO.getBiereConsommees();
        
        if(hmBieresConsommees.size() != 0){

            TextView tx5 = (TextView) findViewById(R.id.textView5);
            tx5.setVisibility(View.GONE);
            

/* On recupére les informations sur les bières qui sont stockées sur le portable */
        Iterator it = hmBieresConsommees.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            key = (Integer) entry.getKey();
            note = (Integer) entry.getValue();

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
                                                                       biere.setCouleur(jsonobject.getString("libelle_couleur"));
                                                                       biere.setFermentation(jsonobject.getString("libelle_fermentation"));
                                                                       biere.setNote(hmBieresConsommees.get(jsonobject.getInt("id_biere")));
                                                                       listeBieres.add(biere);

                                                                   }

                                                               } catch (JSONException exc) {
                                                                   exc.printStackTrace();
                                                               }

                                                               runOnUiThread(new Runnable() {
                                                                   @Override
                                                                   public void run() {
                                                                       ArrayList<Integer> listeIdDesBieres= new ArrayList<Integer>();
                                                                       ArrayList<String> listeNomDesBieres= new ArrayList<String>();
                                                                       ArrayList<String> listeCouleurDesBieres= new ArrayList<String>();
                                                                       ArrayList<String> listeFermentationDesBieres= new ArrayList<String>();
                                                                       ArrayList<String> listeNoteDesBieres= new ArrayList<String>();

                                                                       for(Biere b: listeBieres){
                                                                           listeIdDesBieres.add(b.getId());
                                                                           listeNomDesBieres.add(b.getNom());
                                                                           listeCouleurDesBieres.add(b.getCouleur());
                                                                           listeFermentationDesBieres.add(b.getFermentation());
                                                                           String s=""+b.getNote();
                                                                           listeNoteDesBieres.add(s+"/5");
                                                                       }
                                                                       

                                                                       String[] names = new String[listeNomDesBieres.size()];
                                                                       for (int i = 0; i < listeNomDesBieres.size(); i++) {
                                                                           names[i] = listeNomDesBieres.get(i);
                                                                       }

                                                                       String[] couleurs = new String[listeNomDesBieres.size()];
                                                                       for (int i = 0; i < listeNomDesBieres.size(); i++) {
                                                                           couleurs[i] = listeCouleurDesBieres.get(i);
                                                                       }

                                                                       String[] fermentations = new String[listeNomDesBieres.size()];
                                                                       for (int i = 0; i < listeNomDesBieres.size(); i++) {
                                                                           fermentations[i] = listeFermentationDesBieres.get(i);
                                                                       }
                                                                       

                                                                       String[] notes = new String[listeNomDesBieres.size()];
                                                                       for (int i = 0; i < listeNomDesBieres.size(); i++) {
                                                                           notes[i] = listeNoteDesBieres.get(i);
                                                                       }

                                                                       ArrayList<ListItem> myList = new ArrayList<ListItem>();

                                                                       for (int i = 0; i < names.length; i++) {

                                                                           ListItem li= new ListItem(names[i], couleurs[i], fermentations[i], notes[i]);
                                                                           li.setNote(notes[i]);
                                                                           myList.add(li);

                                                                           Log.i("aa", li.getName()+" : "+li.getNote());

                                                                       }
                                                                       

                                                                       MonAdapteur adapter = new MonAdapteur(BieresConsommees.this, myList); lv.setAdapter(adapter);

                                                                       lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
                                                                       {
                                                                           @Override
                                                                           public void onItemClick(AdapterView<?> adapter, View v, int position,
                                                                                                   long arg3)
                                                                           {
                                                                               ListItem i= (ListItem) adapter.getItemAtPosition(position);
                                                                               String value = (String) i.toString();

                                                                               for(Biere b: listeBieres){
                                                                                   if (b.getNom().equals(value)){
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

    @Override
    public void onResume(){
        super.onResume();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("noteNouvelle");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                    //Write your code if there's no result
            }
        }
    }//onActivityResult

}
