package ru.popovich.shopclient.models;

import java.util.List;

/**
 * Created by gorynych on 13.11.17.
 */

public class ProdCategory {
    private String name;
    private List<ModelProduct> products;
    private int count;

    public ProdCategory() {
    }

    public ProdCategory(String name, List<ModelProduct> products) {
        this.name = name;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ModelProduct> getProducts() {
        return products;
    }

    public void setProducts(List<ModelProduct> products) {
        this.products = products;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
