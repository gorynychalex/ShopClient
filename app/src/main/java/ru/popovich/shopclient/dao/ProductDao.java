package ru.popovich.shopclient.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import ru.popovich.shopclient.models.Product;

/**
 * Dao for products
 */

@Dao
public interface ProductDao {

    @Query("Select * from product")
    List<Product> loadAll();

    @Query("SELECT * FROM product WHERE id IN (:prodIds)")
    List<Product> loadAllByProductId(int... prodIds);

    @Query("SELECT * FROM product WHERE name like :name LIMIT 1")
    Product loadOneByName(String name);

    @Insert
    void insertAll(Product... products);

    @Delete
    void delete(Product product);
}
