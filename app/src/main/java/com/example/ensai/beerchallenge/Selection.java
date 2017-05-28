package com.example.ensai.beerchallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
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

public class Selection extends AppCompatActivity {

    ListView lv ;
    private static ArrayList<Biere> listeDesBieresSelectionnees;

    public  static String Non_BIERE="Non_BIERE";
    public  static String Non_COULEUR="Non_COULEUR";
    public  static String Non_FERMENTATION="Non_FERMENTATION";
    public  static String Non_DEGRE="Non_DEGRE";
    public static String Non_TYPE="Non_TYPE";
    public  static String Non_BRASSERIE="Non_BRASSERIE";
    public  static String Non_ID="Non_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        // intent
        final Intent intent = getIntent();
        String message = intent.getStringExtra(RechercheFormulaire.Nom_BIERE);
        final TextView tv1 = (TextView)findViewById( R.id.resNomBiere );
        tv1.setText( message );
        String message2 = intent.getStringExtra(RechercheFormulaire.Couleur_BIERE);
        final TextView tv2 = (TextView)findViewById( R.id.resCouleur );
        tv2.setText( message2 );
        String message3 = intent.getStringExtra(RechercheFormulaire.Fermentation_BIERE);
        final TextView tv3 = (TextView)findViewById( R.id.resFermentation );
        tv3.setText( message3 );

        String req="http://alban.guichard.free.fr//BeerChallenge/rechercheBiere.php?" + "couleur=" + message2 + "&nom=" + message + "&fermentation=" + message3;

        lv = (ListView) findViewById(R.id.RechercheBieres);

        OkHttpClient okhttpClient = new OkHttpClient();
        Request myGetRequest = new Request.Builder().url(req).build();

        okhttpClient.newCall(myGetRequest).enqueue(new Callback() {

               public void onFailure(Request request, IOException e) {
                   Log.i("Fail", request.toString());

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
                           final ArrayAdapter<String> adapter = new ArrayAdapter<String>(Selection.this,
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
                                           String idString= String.valueOf(b.getId());
                                           Intent i1 = new Intent(v.getContext(), FicheBiere.class);
                                           i1.putExtra("Non_ID", b.getId());
//                                           i1.putExtra(Non_BIERE, b.getNom());
//                                           i1.putExtra(Non_COULEUR, b.getCouleur());
//                                           i1.putExtra(Non_FERMENTATION, b.getFermentation());
//                                           i1.putExtra(Non_DEGRE, b.getDegre_alcool().toString());
//                                           i1.putExtra(Non_TYPE, b.getType());
//                                           i1.putExtra(Non_BRASSERIE, b.getBrasserie());
                                           startActivityForResult(i1, 6);
                                       };
                                   }



//                                   Log.i("Getparent", getParent().toString());

                               }
                           });
                       }
                   });
                   // fin runOnUiThread
                   listeDesBieresSelectionnees=listeBieres;
               }
           }
        );
    }

    public ArrayList<Biere> getListeDesBieresSelectionnees(){
        return listeDesBieresSelectionnees;
    }


}
