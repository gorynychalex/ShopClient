package ru.popovich.shopclient.db;

import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.popovich.shopclient.AppExecutors;
import ru.popovich.shopclient.db.dao.ProductDao;
import ru.popovich.shopclient.db.entity.CustomerEntity;
import ru.popovich.shopclient.db.entity.ProductCategoryEntity;
import ru.popovich.shopclient.db.entity.ProductCommentEntity;
import ru.popovich.shopclient.db.entity.ProductEntity;


//@Database(entities = {ProductEntity.class}, version = 6)
public abstract class SimpleProductDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "simple-db-3";
    private static SimpleProductDatabase instanseDB;

    private static Executor appExecutors;

    public abstract ProductDao productDao();

    public static SimpleProductDatabase getInstanseDB(final Context context){
        if(instanseDB == null){
            synchronized (SimpleProductDatabase.class){
                if(instanseDB == null){
                    appExecutors = Executors.newSingleThreadExecutor();
                    instanseDB = Room.databaseBuilder(context.getApplicationContext(),
                            SimpleProductDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instanseDB;
    }

    public static void insertProduct(final ProductEntity product){
        appExecutors.execute(()-> {
                instanseDB.productDao().insertAll(product);
        });
    }
}
