package ru.popovich.shopclient.data;

import android.icu.util.MeasureUnit;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

import ru.popovich.shopclient.db.entity.ProductCategoryEntity;
import ru.popovich.shopclient.db.entity.ProductEntity;

/**
 * Data Generator for data init DB
 */

public class DBDataGenerator {

    public static List<ProductCategoryEntity> getProductCategories(){

        List<ProductCategoryEntity> productCategoryEntities = new ArrayList<>();

        productCategoryEntities.add(new ProductCategoryEntity("Snaks"));
        productCategoryEntities.add(new ProductCategoryEntity("Drinks"));
        productCategoryEntities.add(new ProductCategoryEntity("Pizza"));
        productCategoryEntities.add(new ProductCategoryEntity("SeaFoods"));

        return productCategoryEntities;
    }

    public static List<ProductEntity> getProducts(){

        List<ProductEntity> productEntities = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            productEntities.add(new ProductEntity(1,"HotDog",1F,1));
            productEntities.add(new ProductEntity(1,"Sandwich",1F,1));
            productEntities.add(new ProductEntity(1, "Squid", 1F, 100, MeasureUnit.GRAM));
            productEntities.add(new ProductEntity(2, "Beer", 1F, 500, MeasureUnit.MILLILITER));
            productEntities.add(new ProductEntity(2, "Grog", 2F, 500, MeasureUnit.MILLILITER));
            productEntities.add(new ProductEntity(2, "Juice", 1F, 500, MeasureUnit.MILLILITER));
            productEntities.add(new ProductEntity(3, "Margarita", 1F, 500, MeasureUnit.GRAM));
            productEntities.add(new ProductEntity(3, "4 seasons", 2F, 750, MeasureUnit.GRAM));
            productEntities.add(new ProductEntity(3, "Boloniez", 3F, 900, MeasureUnit.GRAM));
            productEntities.add(new ProductEntity(4, "Squid", 2F, 300, MeasureUnit.GRAM));
            productEntities.add(new ProductEntity(4, "Salmon smoke", 5F, 200, MeasureUnit.GRAM));
        }
        return productEntities;
    }
}
