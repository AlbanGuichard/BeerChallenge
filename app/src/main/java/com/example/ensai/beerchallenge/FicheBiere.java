package com.example.ensai.beerchallenge;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Map;

/**
 * Created by ensai on 27/05/17.
 */
public class FicheBiere  extends AppCompatActivity {

    public static Biere biere = new Biere();
    public static int buttonvisibility = 1 ;
    private static Map<Integer, Integer> hmBieresConsommees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        final Intent intent = getIntent();

        int idBiere = intent.getIntExtra("IDBiere",0);

        /* On regarde si la bière est dans la base de données locale : ie elle a déjà été consommée */
        SQLiteDatabase db = new BaseDeDonnees(this).getReadableDatabase();
        BiereDAO biereDAO = new BiereDAO(db);
        hmBieresConsommees = biereDAO.getBiereConsommees();

        if(hmBieresConsommees.containsKey(idBiere)){
             buttonvisibility = 0 ;
        }

        /* Recherche de la bière dans la base de données */
        String myurl = "http://alban.guichard.free.fr/BeerChallenge/rechercheBiereParID.php?" + "id=" + idBiere;

        OkHttpClient okhttpClient = new OkHttpClient();
        Request myGetRequest = new Request.Builder().url(myurl).build();

            okhttpClient.newCall(myGetRequest).enqueue(new Callback() {

                                                           public void onFailure(Request request, IOException e) {
                                                           }

                                                           public void onResponse(Response response) throws IOException {

                                                               try {
                                                                   String text = response.body().string();
                                                                   JSONArray json = new JSONArray(text);

                                                                       JSONObject jsonobject = json.getJSONObject(0);

                                                                       biere.setId(jsonobject.getInt("id_biere"));
                                                                       biere.setNom(jsonobject.getString("nom"));
                                                                       biere.setBrasserie(jsonobject.getString("nom_brasserie"));
                                                                       biere.setCouleur(jsonobject.getString("libelle_couleur"));
                                                                       biere.setDegre_alcool(jsonobject.getDouble("degre_alcool"));
                                                                       biere.setFermentation(jsonobject.getString("libelle_fermentation"));
                                                                       biere.setType(jsonobject.getString("libelle_type"));

                                                               } catch (JSONException exc) {
                                                                   exc.printStackTrace();
                                                               }

                                                               runOnUiThread(new Runnable() {
                                                                   @Override
                                                                   public void run() {
                                                                       setContentView(R.layout.fichebiere);

                                                                       final TextView tv1 = (TextView) findViewById(R.id.NomDeLaBiere);
                                                                       tv1.setText(biere.getNom());

                                                                       final TextView tv2 = (TextView) findViewById(R.id.CouleurDeLaBiere);
                                                                       tv2.setText("Couleur : "+biere.getCouleur());

                                                                       final TextView tv3 = (TextView) findViewById(R.id.FermentationDeLaBiere);
                                                                       tv3.setText("Fermentation : "+biere.getFermentation());

                                                                       final TextView tv4 = (TextView) findViewById(R.id.DegreDeLaBiere);
                                                                       tv4.setText("Degré d'alcool : "+biere.getDegre_alcool());

                                                                       final TextView tv5 = (TextView) findViewById(R.id.TypeDeLaBiere);
                                                                       tv5.setText("Type : "+biere.getType());

                                                                       final TextView tv6 = (TextView) findViewById(R.id.BrasserieDeLaBiere);
                                                                       tv6.setText("Brasserie : "+biere.getBrasserie());


                                                                   }
                                                               }); // fin runOnUiThread

                                                           }
                                                       }
            );

    }

    public void clickButtonBue(View v){
//
//        Intent i1 = new Intent(v.getContext(), FicheBiereBue.class);
//        final TextView tv1 = (TextView) findViewById(R.id.NomDeLaBiere);
//        i1.putExtra(Non_BIEREpb, tv1.getText());
//        final TextView tv2 = (TextView) findViewById(R.id.CouleurDeLaBiere);
//        i1.putExtra(Non_COULEURpb, tv2.getText());
//        final TextView tv3 = (TextView) findViewById(R.id.FermentationDeLaBiere);
//        i1.putExtra(Non_FERMENTATIONpb, tv3.getText());
//        final TextView tv4 = (TextView) findViewById(R.id.DegreDeLaBiere);
//        i1.putExtra(Non_DEGREpb, tv4.getText());
//        final TextView tv5 = (TextView) findViewById(R.id.TypeDeLaBiere);
//        i1.putExtra(Non_TYPEpb, tv5.getText());
//        final TextView tv6 = (TextView) findViewById(R.id.BrasserieDeLaBiere);
//        i1.putExtra(Non_BRASSERIEpb, tv6.getText());
//        TextView tv7 = (TextView) findViewById(R.id.IdDeLaBiere);
//        String idString=tv7.getText().toString();
//        Toast.makeText(v.getContext(), idString, Toast.LENGTH_SHORT).show();
//        i1.putExtra(Non_IDpb, idString);
//        startActivityForResult(i1, 6);

    }
}
