package ru.popovich.shopclient.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import ru.popovich.shopclient.data.DBDataRepository;
import ru.popovich.shopclient.db.entity.ProductCategoryEntity;
import ru.popovich.shopclient.db.entity.ProductEntity;

/**
 * Created by gorynych on 08.01.18.
 */

public class ProductViewModel extends AndroidViewModel {

    private final LiveData<ProductCategoryEntity> mObservableProductCategory;
    private final LiveData<List<ProductEntity>> mObservableProduct;

    private final int mProductCategoryId;



    public ProductViewModel(@NonNull Application application, DBDataRepository repository, int mProductCategoryId) {
        super(application);
        this.mObservableProductCategory = repository.loadCategoryByIdLive(mProductCategoryId);
        this.mObservableProduct = repository.loadProductsByCategoryId(mProductCategoryId);
        this.mProductCategoryId = mProductCategoryId;
    }
}
