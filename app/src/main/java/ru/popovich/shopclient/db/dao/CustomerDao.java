package ru.popovich.shopclient.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import ru.popovich.shopclient.db.entity.CustomerEntity;

@Dao
public interface CustomerDao {
    @Query("select * from customer")
    List<CustomerEntity> loadAll();

    @Query("SELECT * FROM customer WHERE id IN (:customerIds)")
    List<CustomerEntity> loadAllByProductId(int... customerIds);

    @Query("SELECT * FROM customer WHERE name like :name LIMIT 1")
    CustomerEntity loadOneByName(String name);

    @Insert
    void insertAll(CustomerEntity... customerEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<CustomerEntity> customers);
}
