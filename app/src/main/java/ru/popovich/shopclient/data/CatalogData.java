package ru.popovich.shopclient.data;

import java.util.ArrayList;
import java.util.List;

import ru.popovich.shopclient.R;
import ru.popovich.shopclient.models.Catalog;
import ru.popovich.shopclient.models.ModelProduct;
import ru.popovich.shopclient.models.ProdCategory;

/**
 * Created by gorynych on 27.11.17.
 */

public class CatalogData {
    private static Catalog catalogs ;
    private static List<ProdCategory> prodCategories1 ;
    private static List<ProdCategory> prodCategories2;
    private static List<ProdCategory> prodCategories3;
    private static List<ModelProduct> products1;
    private static List<ModelProduct> products2;
    private static List<ModelProduct> products3;

    public static void setCatalogs(){
        catalogs = new Catalog();
        prodCategories1 = new ArrayList<>();
        prodCategories2 = new ArrayList<>();
        List<ProdCategory> prodCategories3 = new ArrayList<>();
        List<ModelProduct> products1 = new ArrayList<>();
        List<ModelProduct> products2 = new ArrayList<>();
        List<ModelProduct> products3 = new ArrayList<>();

        catalogs.setName("Drinks and Snaks");
        //Initialize Products
        products1.add(new ModelProduct("Sandwich", "Rio De Janeiro", R.drawable.sandwich,2F));
        products1.add(new ModelProduct("Hot dog", "Hot Evening", R.drawable.sandwich2, 2.5F));
        products1.add(new ModelProduct("Sanwich", "Reptile", R.drawable.sandwich, 5F));

        prodCategories1.add(new ProdCategory("Sandwichs",products1));

        products2.add(new ModelProduct("Apple Juice", "Apple Juice", R.drawable.shwepps,2F));
        products2.add(new ModelProduct("Orange Juice", "Orange Juice", R.drawable.ic_basket, 2.5F));
        products2.add(new ModelProduct("Strawberry Juice", "Strawberry Juice", R.drawable.shwepps, 5F));

        prodCategories1.add(new ProdCategory("Drinks", products2));

        products3.add(new ModelProduct("Noodle", "Noodle", R.drawable.shwepps,2.0F));
        products3.add(new ModelProduct("Chiken", "Chiken", R.drawable.sandwich, 2.5F));
        products3.add(new ModelProduct("Nagins", "Nagins", R.drawable.ic_basket, 5F));


        prodCategories1.add(new ProdCategory("Drinks", products3));

        catalogs.setCategories(prodCategories1);
    }

    public static Catalog getCatalogs(){
        return catalogs;
    }
}
