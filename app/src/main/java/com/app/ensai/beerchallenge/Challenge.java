package com.app.ensai.beerchallenge;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.util.Map;

/**
 * Created by ensai on 30/05/17.
 */

public class Challenge extends AppCompatActivity {

    ImageView imageUneBiere ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge);

        /* On récupère toutes les images */
        ImageView imageUneBiere = (ImageView) findViewById(R.id.imgUneBiere);
        ImageView imageCinqBiere = (ImageView) findViewById(R.id.imgCinqBiere);
        ImageView imageDixBiere = (ImageView) findViewById(R.id.imgDixBiere);
        ImageView imageVingtBiere = (ImageView) findViewById(R.id.imgVingtBiere);
        ImageView imageCinquanteBiere = (ImageView) findViewById(R.id.imgCinquanteBiere);
        ImageView imageCentBiere = (ImageView) findViewById(R.id.imgCentBiere);

        ImageView imageBiereAmbree = (ImageView) findViewById(R.id.imgBiereAmbree);
        ImageView imageBiereBlanche = (ImageView) findViewById(R.id.imgBiereBlanche);
        ImageView imageBiereBlonde = (ImageView) findViewById(R.id.imgBiereBlonde);
        ImageView imageBiereBrune = (ImageView) findViewById(R.id.imgBiereBrune);

        ImageView imageBiereDouble = (ImageView) findViewById(R.id.imgBiereDouble);
        ImageView imageBiereTriple = (ImageView) findViewById(R.id.imgBiereTriple);

        /* On récupère la liste des challenges dans la base de données locale */
        ChallengeDAO challengeDAO = new ChallengeDAO(Challenge.this);
        Map<String, Integer> hm = challengeDAO.getChallenge();

        if(hm.get("total") <1 )
        {
            imageUneBiere.setColorFilter(Color.GRAY);
            imageCinqBiere.setColorFilter(Color.GRAY);
            imageDixBiere.setColorFilter(Color.GRAY);
            imageVingtBiere.setColorFilter(Color.GRAY);
            imageCinquanteBiere.setColorFilter(Color.GRAY);
            imageCentBiere.setColorFilter(Color.GRAY);
        }
        else if (hm.get("total")>=1 && hm.get("total") <5){
            imageCinqBiere.setColorFilter(Color.GRAY);
            imageDixBiere.setColorFilter(Color.GRAY);
            imageVingtBiere.setColorFilter(Color.GRAY);
            imageCinquanteBiere.setColorFilter(Color.GRAY);
            imageCentBiere.setColorFilter(Color.GRAY);
        }
        else if (hm.get("total")>=5 && hm.get("total") <10){
            imageDixBiere.setColorFilter(Color.GRAY);
            imageVingtBiere.setColorFilter(Color.GRAY);
            imageCinquanteBiere.setColorFilter(Color.GRAY);
            imageCentBiere.setColorFilter(Color.GRAY);
        }
        else if (hm.get("total")>=10 && hm.get("total") <20){
            imageVingtBiere.setColorFilter(Color.GRAY);
            imageCinquanteBiere.setColorFilter(Color.GRAY);
            imageCentBiere.setColorFilter(Color.GRAY);
        }
        else if (hm.get("total")>=20 && hm.get("total") <50){
            imageCinquanteBiere.setColorFilter(Color.GRAY);
            imageCentBiere.setColorFilter(Color.GRAY);
        }
        else if (hm.get("total")>=50 && hm.get("total") <100){
            imageCentBiere.setColorFilter(Color.GRAY);
        }
        else{

        }

        if (hm.get("ambree")< 5 ){
            imageBiereAmbree.setColorFilter(Color.GRAY);
        }
        if (hm.get("brune")< 5 ){
            imageBiereBrune.setColorFilter(Color.GRAY);
        }
        if (hm.get("blonde")< 5 ){
            imageBiereBlonde.setColorFilter(Color.GRAY);
        }
        if (hm.get("blanche")< 5 ){
            imageBiereBlanche.setColorFilter(Color.GRAY);
        }


        if (hm.get("triple")< 5 ){
            imageBiereTriple.setColorFilter(Color.GRAY);
        }
        if (hm.get("double")< 5 ){
            imageBiereDouble.setColorFilter(Color.GRAY);
        }




    }

}
