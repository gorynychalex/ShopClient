package ru.popovich.shopclient.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import ru.popovich.shopclient.db.entity.ProductCommentEntity;
import ru.popovich.shopclient.db.entity.ProductEntity;

/**
 * Dao for products
 */

@Dao
public interface ProductDao {

    @Query("select * from product")
    List<ProductEntity> loadAll();

    @Query("SELECT * FROM product WHERE id IN (:prodIds)")
    List<ProductEntity> loadAllByProductId(int... prodIds);

    @Query("SELECT * FROM product WHERE id IN (:categoryIds)")
    LiveData<List<ProductEntity>> loadAllByCategoryId(int... categoryIds);

    @Query("SELECT * FROM product WHERE name like :name LIMIT 1")
    ProductEntity loadOneByName(String name);

    @Insert
    void insertAll(ProductEntity... productEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<ProductEntity> products);

    @Delete
    void delete(ProductEntity productEntity);
}
