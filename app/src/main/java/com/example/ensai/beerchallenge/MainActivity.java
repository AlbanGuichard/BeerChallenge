package com.example.ensai.beerchallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}
