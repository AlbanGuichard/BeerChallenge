package com.app.ensai.beerchallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;


/**
 * Created by ensai on 09/05/17.
 */

public class RechercheFormulaire extends AppCompatActivity {

    public final static String Nom_BIERE ="nom nom";
    public final static String Couleur_BIERE ="couleur de la bière";
    public final static String Fermentation_BIERE ="fermentation de la bière";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recherche_formulaire);
    }

//    protected void autoComplete(){
//        AutoCompleteTextView textView = (AutoCompleteTextView)
//            findViewById(R.id.beers);
//
//    }

    public void clickButtonSelect(View v){
        Intent i1 = new Intent(this, Selection.class);
        EditText ed = (EditText) findViewById(R.id.beers);

        i1.putExtra(Nom_BIERE, ed.getText().toString());
        Spinner s = (Spinner) findViewById(R.id.spinnerCouleur);
        i1.putExtra(Couleur_BIERE, s.getSelectedItem().toString());
        Spinner s2 = (Spinner) findViewById(R.id.spinnerFermentation);
        i1.putExtra(Fermentation_BIERE, s2.getSelectedItem().toString());
        startActivityForResult(i1, 1);
    }

    protected void selectBeer(String name, String couleur, String fermentation){
//

    }


    public void lancerRecherche(View v){
        /*public static final String ACTE_KEY = "id";
        public static final String ACTE_CLASSE = "acte_classe";
        public static final String ACTE_LIBELLE = "acte_libelle";

        public static final String ACTE_TABLE_NAME = "livret_acte";
        public static final String requete_Select = "SELECT id from table where id=1;";*/
    }
}
