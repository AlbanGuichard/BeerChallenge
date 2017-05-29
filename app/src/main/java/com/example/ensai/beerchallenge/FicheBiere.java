package com.example.ensai.beerchallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
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
import java.util.Map;

/**
 * Created by ensai on 27/05/17.
 */
public class FicheBiere  extends AppCompatActivity {

    public static Biere biere = new Biere();
    public static int buttonVisibility;
    public static int ratingVisibility ;
    public static int idBiere ;
    public static int voteBiere ;
    private static Map<Integer, Integer> hmBieresConsommees;
    public static BiereDAO biereDAO ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        final Intent intent = getIntent();

        idBiere = intent.getIntExtra("IDBiere",0);

        /* On regarde si la bière est dans la base de données locale : ie elle a déjà été consommée */
        biereDAO = new BiereDAO(FicheBiere.this);
        hmBieresConsommees = biereDAO.getBiereConsommees();

        // 0 = déjà bu la bière 1 = n' pas encore bu la bière
        if(hmBieresConsommees.containsKey(idBiere)){
             buttonVisibility = 0 ;
            ratingVisibility = 1 ;
            voteBiere = hmBieresConsommees.get(idBiere);
        }
        else{
            buttonVisibility = 1;
            ratingVisibility = 0 ;
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

                                                                       Button monBouton = (Button) findViewById(R.id.buttonBue);
                                                                       RatingBar rt = (RatingBar) findViewById(R.id.rt);

                                                                       if(buttonVisibility ==0){
                                                                           monBouton.setVisibility(View.GONE); // Bouton invisible
                                                                           rt.setVisibility(View.VISIBLE); // Ratingbar visible
                                                                           rt.setRating(voteBiere);
                                                                       }
                                                                       else{
                                                                           monBouton.setVisibility(View.VISIBLE);
                                                                           rt.setVisibility(View.GONE);
                                                                       }

                                                                       ImageButton imageButton =(ImageButton) findViewById(R.id.imagebutton);


                                                                       rt.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

                                                                           @Override
                                                                           public void onRatingChanged(RatingBar rt, float rating,
                                                                                                       boolean fromUser) {
                                                                               Log.i("AA",""+FicheBiere.biereDAO.toString());

                                                                               FicheBiere.biereDAO.setVote(idBiere,(int) rt.getRating());
                                                                           }
                                                                       });

                                                                   }
                                                               }); // fin runOnUiThread

                                                           }
                                                       }
            );

    }

    public void clickButtonBue(View v){

        Toast.makeText(FicheBiere.this,"Et hop ! Une bière de plus",Toast.LENGTH_SHORT).show();
        /* On ajoute la bière dans la base de données locale */
        biereDAO.ajouterNouvelleBiere(idBiere);
        finish();
        startActivity(getIntent());
    }

}
