package ru.popovich.shopclient.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import ru.popovich.shopclient.db.ShopDatabase;
import ru.popovich.shopclient.db.entity.ProductCategoryEntity;
import ru.popovich.shopclient.db.entity.ProductEntity;

/**
 * View Model for Product List
 */

public class ProductListViewModel extends ViewModel {

    private final ShopDatabase shopDatabase;

    private final LiveData<List<ProductEntity>> mObservableProduct;

    // Create a LiveData with a ProductEntity type object
    private MutableLiveData<ProductCategoryEntity> mutableLiveData;

    public ProductListViewModel(ShopDatabase shopDatabase, LiveData<List<ProductEntity>> mObservableProduct) {
        this.shopDatabase = shopDatabase;
        this.mObservableProduct = mObservableProduct;

        if(mutableLiveData == null)
            mutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<ProductCategoryEntity> getMutableLiveData(){
        if(mutableLiveData == null)
            mutableLiveData = new MutableLiveData<>();

        return mutableLiveData;
    }

    private void loadData(){





    }
}
