package ru.popovich.shopclient.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import ru.popovich.shopclient.db.entity.ProductCategoryEntity;

/**
 * View Model for Product List
 */

public class ProductListViewModel extends ViewModel {

    // Create a LiveData with a ProductEntity type object
    private MutableLiveData<ProductCategoryEntity> mutableLiveData;

    public MutableLiveData<ProductCategoryEntity> getMutableLiveData(){
        if(mutableLiveData == null)
            mutableLiveData = new MutableLiveData<>();


        return mutableLiveData;
    }
}
