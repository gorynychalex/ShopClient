package ru.popovich.shopclient.models;

import java.util.Comparator;

/**
 * Model product for basket
 */

public class ModelProductBasket {

    private ModelProduct product;
    private int count;
    private float sumPrice;
    private int sumAmount;

    public ModelProductBasket() {
    }

    public ModelProductBasket(ModelProduct product, int count) {
        this.product = product;
        setCount(count);
    }

    public ModelProduct getProduct() {
        return product;
    }

    public void setProduct(ModelProduct product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        this.sumPrice = product.getPrice() * count;
        this.sumAmount = product.getAmount() * count;
    }

    public float getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(float sumPrice) {
        this.sumPrice = sumPrice;
    }

    public int getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(int sumAmount) {
        this.sumAmount = sumAmount;
    }
}
