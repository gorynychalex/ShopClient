package ru.popovich.shopclient.models;

import android.support.annotation.DrawableRes;

public class ModelProduct {
    private String onCardText;
    private String underCardText;
    private int pictures;
    private float price;

    public ModelProduct() {
    }

    public ModelProduct(String onCardText, String underCardText, int pictures, float price) {
        this.onCardText = onCardText;
        this.underCardText = underCardText;
        this.pictures = pictures;
        this.price = price;
    }

    public String getOnCardText() {
        return onCardText;
    }

    public void setOnCardText(String onCardText) {
        this.onCardText = onCardText;
    }

    public String getUnderCardText() {
        return underCardText;
    }

    public void setUnderCardText(String underCardText) {
        this.underCardText = underCardText;
    }

    public int getPictures() {
        return pictures;
    }

    public void setPictures(int pictures) {
        this.pictures = pictures;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
