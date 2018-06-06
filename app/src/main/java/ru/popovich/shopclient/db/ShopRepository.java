package ru.popovich.shopclient.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import ru.popovich.shopclient.db.entity.ProductEntity;
import ru.popovich.shopclient.models.Catalog;

public class ShopRepository {

    private static ShopRepository shopRepositoryInstance;
//    private final ShopDatabase shopDatabase;
    private final ShopDatabase shopDatabase;

    private MediatorLiveData<Catalog> mediatorLiveData = new MediatorLiveData<>();

    private MutableLiveData<List<ProductEntity>> listMutableLiveData = new MutableLiveData<>();

    private ShopRepository (final ShopDatabase shopDatabase){
        this.shopDatabase = shopDatabase;
    }
//
//    private ShopRepository (final ShopDatabase shopDatabase){
//        this.shopDatabase = shopDatabase;
//    }
    public static ShopRepository getShopRepositoryInstance(final ShopDatabase shopDatabase){
//    public static ShopRepository getShopRepositoryInstance(final SimpleProductDatabase shopDatabase){

        if(shopRepositoryInstance == null){
            synchronized (ShopRepository.class){
                if(shopRepositoryInstance == null){
                    shopRepositoryInstance = new ShopRepository(shopDatabase);
                }
            }
        }
        return shopRepositoryInstance;
    }

//    public List<ProductEntity> getProducts(){
//        return shopDatabase.productDao().loadAll();
//    }

    public LiveData<List<ProductEntity>> getProducts(){
        return shopDatabase.productDao().loadAllLive();
    }

    public LiveData<List<ProductEntity>> getProductsLive(){
        return shopDatabase.productDao().loadAllLive();
    }

    public void insertData(ProductEntity productEntity){

    }
}
