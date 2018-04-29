package ru.popovich.shopclient.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ru.popovich.shopclient.db.entity.ProductCategoryEntity;
import ru.popovich.shopclient.db.entity.ProductEntity;

/**
 * Created by gorynych on 24.12.17.
 */

@Dao
public interface ProductCategoryDao {

    @Query("Select * FROM product_category")
    LiveData<List<ProductCategoryEntity>> loadAll();

    @Query("Select * FROM product_category WHERE id IN (:categIds)")
    List<ProductCategoryEntity> loadAllByCategoryId(int... categIds);

    @Query("Select * FROM product_category WHERE id IN (:categIds)")
    LiveData<ProductCategoryEntity> loadCategoryByIdLive(int... categIds);

    @Insert
    void insert(ProductCategoryEntity... productCategories);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<ProductCategoryEntity> productCategoryEntities);

    @Update
    void update(ProductCategoryEntity... productCategoryEntities);

    @Delete
    void delete(ProductCategoryEntity productCategoryEntity);
}
