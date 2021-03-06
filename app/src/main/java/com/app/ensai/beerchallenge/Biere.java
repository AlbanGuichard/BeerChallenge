package com.app.ensai.beerchallenge;

/**
 * Created by ensai on 23/05/17.
 */

public class Biere {

    private int id ;
    private String nom ;
    private String brasserie ;
    private String fermentation ;
    private Double degre_alcool ;
    private String couleur ;
    private String type ;
    private int note ;

    public Biere(int id, String nom, String brasserie, String fermentation, Double degre_alcool, String couleur, String type, int note) {
        this.id = id;
        this.nom = nom;
        this.brasserie = brasserie;
        this.fermentation = fermentation;
        this.degre_alcool = degre_alcool;
        this.couleur = couleur;
        this.type = type;
        this.note = note ;
    }


    public Biere() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getBrasserie() {
        return brasserie;
    }

    public void setBrasserie(String brasserie) {
        this.brasserie = brasserie;
    }

    public String getFermentation() {
        return fermentation;
    }

    public void setFermentation(String fermentation) {
        this.fermentation = fermentation;
    }

    public Double getDegre_alcool() {
        return degre_alcool;
    }

    public void setDegre_alcool(Double degre_alcool) {
        this.degre_alcool = degre_alcool;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
}
