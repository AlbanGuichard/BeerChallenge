package com.app.ensai.beerchallenge;

import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_main);
    }

    public void clickButtonRecherche(View v) {
        Intent intent = new Intent(this, RechercheFormulaire.class);
        startActivity(intent);
    }

    public void clickButtonToutesLesBieres(View v) {
        Intent intent = new Intent(this, ToutesLesBieres.class);
        startActivity(intent);
    }

    public void clickButtonCatalogue(View v) {
        Intent intent = new Intent(this, BieresConsommees.class);
        startActivity(intent);
    }

    public void clickButtonChallenge(View v) {
        Intent intent = new Intent(this, Challenge.class);
        startActivity(intent);
    }

    public void clickRechercherBar(View v) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=bar, bars");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void clickButtonCredit(View v) {
        Intent intent = new Intent(this, Credit.class);
        startActivity(intent);
    }
}
