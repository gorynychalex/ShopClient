package ru.popovich.shopclient.models;

import android.content.Intent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Basket class
 */

public class Basket {

    Map<ModelProduct, ModelProductBasket> productMap;
    List<ModelProductBasket> productBaskets;

    Set<ModelProduct> productSet;
    Set<ModelProductBasket> productBasketSet;

    List<ModelProduct> products;
    List<Integer> counterProduct;
    double sum;

    {
        productMap = new HashMap<>();
    }

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
        for(Map.Entry<ModelProduct,ModelProductBasket> product: productMap.entrySet()){
            sum += product.getValue().getSumPrice();
        }

        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }


    public Set<ModelProduct> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<ModelProduct> productSet) {
        this.productSet = productSet;
    }

    public Set<ModelProductBasket> getProductBasketSet() {
        return productBasketSet;
    }

    public void setProductBasketSet(Set<ModelProductBasket> productBasketSet) {
        this.productBasketSet = productBasketSet;
    }

    public void addProductBasketSet(ModelProduct product, int count){
        productSet.add(product);
        productBasketSet.add(new ModelProductBasket(product,count));
    }

    public void addProductMap(ModelProduct product, int count){
        int c = 0;
        for(Map.Entry<ModelProduct,ModelProductBasket> mod: productMap.entrySet()) {
            if(mod.getKey().getUnderCardText().equals(product.getUnderCardText())){
                c = mod.getValue().getCount();
            }
        }

        productMap.put(product, new ModelProductBasket(product,c + count));
    }

    public Map<ModelProduct, ModelProductBasket> getProductMap() {
        return productMap;
    }

    @Override
    public String toString() {

        StringBuilder prods = new StringBuilder();
        for(ModelProduct product: products)
            prods.append(product.getUnderCardText() + "\t");

        prods.append("\n");

        for(Integer c: counterProduct)
            prods.append(c + "\t");

        prods.append("\nSets:\n");

        for(ModelProductBasket p: productBasketSet){
            prods.append(p.getProduct().getOnCardText() + "\t");
        }

        prods.append("\nMAP:\n");

        for(Map.Entry<ModelProduct,ModelProductBasket> mod: productMap.entrySet()){
            prods.append(mod.getKey().getUnderCardText() + "\t" + mod.getKey().getPrice() * mod.getValue().getCount() + "\t");
            prods.append("\n");

        }

        return "there are: \n" + String.valueOf(prods);
    }
}
