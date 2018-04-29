package ru.popovich.shopclient.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import ru.popovich.shopclient.db.entity.ProductCommentEntity;

/**
 * Created by gorynych on 06.01.18.
 */

@Dao
public interface ProductCommentDao {
    @Query("SELECT * FROM comments where productId = :productId")
    LiveData<List<ProductCommentEntity>> loadCommentsByProductId(int productId);

    @Query("SELECT * FROM comments where productId = :productId")
    List<ProductCommentEntity> loadCommentsSync(int productId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ProductCommentEntity> products);
}
