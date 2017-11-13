package ru.popovich.shopclient.models;

import java.util.List;

/**
 * Model Catalog and ProdCategory
 */

public class Catalog {
    private String name;
    private List<ProdCategory> categories;
    private int count;
    private int count_products;

    public Catalog() {
    }

    public Catalog(String name, List<ProdCategory> categories) {
        this.name = name;
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProdCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ProdCategory> categories) {
        this.categories = categories;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount_products() {
        return count_products;
    }

    public void setCount_products(int count_products) {
        this.count_products = count_products;
    }
}

