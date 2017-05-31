package com.app.ensai.beerchallenge;

/**
 * Created by ensai on 30/05/17.
 */


public class ListItem {

    private String name;
    private String couleur;
    private String fermentation;
    private Integer image;
    private String note;


    public ListItem(String name, String couleur, String fermentation, String note) {
// public ListItem(String name, String couleur, String fermentation, Integer image, String note) {
        this.name = name;
        this.couleur = couleur;
        this.fermentation = fermentation;
// this.image = image;
        this.note = note;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getFermentation() {
        return fermentation;
    }

    public void setFermentation(String fermentation) {
        this.fermentation = fermentation;
    }

// public Integer getImage() {
// return image;
// }
//
// public void setImage(Integer image) {
// this.image = image;
// }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}