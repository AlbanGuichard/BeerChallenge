package com.app.ensai.beerchallenge;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_main);
    }

    public void clickButtonRecherche(View v){
        Intent intent = new Intent(this, RechercheFormulaire.class);
        startActivity(intent);
    }

    public void clickButtonToutesLesBieres(View v){
        Intent intent = new Intent(this, ToutesLesBieres.class);
        startActivity(intent);
    }

    public void clickButtonCatalogue(View v){
        Intent intent = new Intent(this, BieresConsommees.class);
        startActivity(intent);
    }

    public void clickRechercherBar(View v){
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=bar");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);

    }
}
