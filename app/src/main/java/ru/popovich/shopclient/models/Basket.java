package ru.popovich.shopclient.models;

import android.content.Intent;

import java.util.Arrays;
import java.util.List;

/**
 * Basket class
 */

public class Basket {

    List<ModelProduct> products;
    List<Integer> counterProduct;
    double sum;

    public List<ModelProduct> getProducts() {
        return products;
    }

    public void setProducts(List<ModelProduct> products) {
        this.products = products;
    }

    public List<Integer> getCounterProduct() {
        return counterProduct;
    }

    public void setCounterProduct(List<Integer> counterProduct) {
        this.counterProduct = counterProduct;
    }

    public double getSum() {

        int i = 0;
        for(ModelProduct product: products){
            sum += (product.getPrice() * counterProduct.get(i++));
        }

        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {

        String prods = null;
        for(ModelProduct product: products)
            prods += product.getUnderCardText() + "\t";

        String counter = null;
        for(Integer c: counterProduct)
            counter += c + "\t";

        return "there are: \n" + prods + "\n" + counter;
    }
}
