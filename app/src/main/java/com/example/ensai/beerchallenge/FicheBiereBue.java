package com.example.ensai.beerchallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class FicheBiereBue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_biere_bue);

        // intent
        final Intent intent = getIntent();
        Bundle b= intent.getExtras();

//        String message = intent.getStringExtra(FicheBiere.Non_BIEREpb);
//        final TextView tv1 = (TextView) findViewById(R.id.NomDeLaBiereBue);
//        tv1.setText(message);
////        Toast.makeText(FicheBiere.this, message, Toast.LENGTH_SHORT).show();
//
//        String message2 = intent.getStringExtra(FicheBiere.Non_COULEURpb);
//        final TextView tv2 = (TextView) findViewById(R.id.CouleurDeLaBiereBue);
//        tv2.setText((String) b.get("Non_COULEUR"));
//
//        String message3 = intent.getStringExtra(FicheBiere.Non_FERMENTATIONpb);
//        final TextView tv3 = (TextView) findViewById(R.id.FermentationDeLaBiereBue);
//        tv3.setText(message3);
//
//        String message4 = intent.getStringExtra(FicheBiere.Non_DEGREpb);
//        final TextView tv4 = (TextView) findViewById(R.id.DegreDeLaBiereBue);
//        tv4.setText(message4);
//
//        String message5 = intent.getStringExtra(FicheBiere.Non_TYPEpb);
//        final TextView tv5 = (TextView) findViewById(R.id.TypeDeLaBiereBue);
//        tv5.setText(message5);
//
//        String message6 = intent.getStringExtra(FicheBiere.Non_BRASSERIEpb);
//        final TextView tv6 = (TextView) findViewById(R.id.BrasserieDeLaBiereBue);
//        tv6.setText(message6);
//
//        String message7 = intent.getStringExtra(FicheBiere.Non_IDpb);
//        final TextView tv7 = (TextView) findViewById(R.id.IdDeLaBiereBue);
//        tv7.setText(message7);

    }
}
