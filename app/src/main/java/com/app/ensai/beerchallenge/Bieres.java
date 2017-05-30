//package com.example.ensai.beerchallenge;
//
//import android.util.Log;
//
//import com.squareup.okhttp.Callback;
//import com.squareup.okhttp.OkHttpClient;
//import com.squareup.okhttp.Request;
//import com.squareup.okhttp.Response;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//
///**
// * Created by ensai on 23/05/17.
// */
//
//public class Bieres {
//
//    private ArrayList<Biere> listeBieres;
//    private String text;
//
//
//
//    public Bieres() {
//super();
//
//
//
////        try {
////            String myurl= "http://alban.guichard.free.fr/BeerChallenge/json.php";
////
////            URL url = new URL(myurl);
////            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
////            connection.connect();
////            InputStream inputStream = connection.getInputStream();
////            /*
////             * InputStreamOperations est une classe complémentaire:
////             * Elle contient une méthode InputStreamToString.
////             */
////            String result = InputStreamOperations.InputStreamToString(inputStream);
////
////            Log.i("result", result.toString());
////
////            // On récupère le JSON complet
////            JSONObject jsonObject = new JSONObject(result);
////            Log.i("ListeBieres",jsonObject.toString());
////            // On récupère le tableau d'objets qui nous concernent
////            JSONArray array = new JSONArray(jsonObject.getString(""));
////            // Pour tous les objets on récupère les infos
////            for (int i = 0; i < array.length(); i++) {
////                // On récupère un objet JSON du tableau
////                JSONObject obj = new JSONObject(array.getString(i));
////                // On fait le lien Personne - Objet JSON
////                Biere biere = new Biere();
////                biere.setNom(obj.getString("nom"));
////                listeBieres.add(biere);
////
////            }
////
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//
//
//        String myurl = "http://alban.guichard.free.fr/BeerChallenge/json.php";
//
//        OkHttpClient okhttpClient = new OkHttpClient();
//        Request myGetRequest = new Request.Builder().url(myurl).build();
//
//        okhttpClient.newCall(myGetRequest).enqueue(new Callback() {
//
//                                                       public void onFailure(Request request, IOException e) {
//                                                           Log.i("Fail", request.toString());
//
//                                                       }
//
//                                                       public void onResponse(Response response) throws IOException {
//
//                                                           Log.i("Marche", response.toString());
//
//                                                           try {
//                                                               text = response.body().string();
//                                                               Log.i("XX", text.toString());
//                                                               JSONArray json = new JSONArray(text);
//                                                               Log.i("test", json.toString());
//
//
//                                                               for (int i = 0; i < json.length(); i++) {
//                                                                   JSONObject jsonobject = json.getJSONObject(i);
//                                                                   Biere biere = new Biere();
//                                                                   biere.setNom(jsonobject.getString("nom"));
//                                                                   // Log.i("Biere",biere.getNom());
//                                                                   listeBieres.add(biere);
//
//                                                               }
//
//
//                                                           } catch (JSONException exc) {
//
//                                                               exc.printStackTrace();
//                                                           }
//                                                           Log.i("LB5", "" + listeBieres.size());
//                                                       }
//
//                                                   }
//        );
//
//
//    }
//
//    public ArrayList<Biere> getListeBieres(){
//
//        return listeBieres ;
//
//    }
//
//
//}
//
