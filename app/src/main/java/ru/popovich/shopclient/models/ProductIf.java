package ru.popovich.shopclient.models;

/**
 * Product Interface
 */

public interface ProductIf {
    int getId();
    String getName();
    String getDescription();
    float getPrice();
    float getMeasure();
    String getPictureUrl();
}
