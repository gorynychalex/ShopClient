package ru.popovich.shopclient.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Basket class
 */

public class Basket {

    List<ModelProductBasket> products;

    double bill;

    {
        products = new ArrayList<>();
    }

    public void addProduct(ModelProduct product, int count){

        //Look at products if found, then set count and var foundProduct
        boolean foundProduct = false;
        for (ModelProductBasket productBasket : products) {
            if (productBasket.equals(product)) {
                productBasket.setCount(productBasket.getCount() + count);
                Log.d("Basket", ".setCountProduct() " + String.valueOf(productBasket.getProduct().getOnCardText()));
                foundProduct = true;
            }
        }

        if(products.isEmpty() || !foundProduct) {
            products.add(new ModelProductBasket(product, count));
            Log.d("Basket", "isEmpty() .addNewProduct() " + String.valueOf(product.getOnCardText()));
        }

        this.bill += product.getPrice() * count;
    }

    public List<ModelProductBasket> getProducts() {
        return products;
    }

    public void setProducts(List<ModelProductBasket> products) {
        this.products = products;
    }

    public double getBill() {

        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    @Override
    public String toString() {

        StringBuilder prods = new StringBuilder();

        prods.append("\n");

        return "there are: \n" + String.valueOf(prods);
    }
}
