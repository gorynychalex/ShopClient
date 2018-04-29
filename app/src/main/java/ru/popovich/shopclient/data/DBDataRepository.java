package ru.popovich.shopclient.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import java.util.List;

import ru.popovich.shopclient.db.ShopDatabase;
import ru.popovich.shopclient.db.entity.ProductCategoryEntity;
import ru.popovich.shopclient.db.entity.ProductCommentEntity;
import ru.popovich.shopclient.db.entity.ProductEntity;

/**
 * Repository handling the work with products
 */

public class DBDataRepository {

    private static DBDataRepository repositoryInstanse;

    private final ShopDatabase shopDatabase;

    private MediatorLiveData<List<ProductCategoryEntity>> mObservableProductCategory;

    private DBDataRepository(final ShopDatabase shopDatabase) {
        this.shopDatabase = shopDatabase;
        mObservableProductCategory = new MediatorLiveData<>();

        mObservableProductCategory.addSource(this.shopDatabase.productCategoryDao().loadAll(),
                productCategoryEntities -> {
                    if(this.shopDatabase.getDBCreated().getValue() != null){
                        mObservableProductCategory.postValue(productCategoryEntities);
                    }
                });
    }

    public static DBDataRepository getInstance(final ShopDatabase database) {
        if (repositoryInstanse == null) {
            synchronized (DBDataRepository.class) {
                if (repositoryInstanse == null) {
                    repositoryInstanse = new DBDataRepository(database);
                }
            }
        }
        return repositoryInstanse;
    }


    /**
     * Get the list of products from the database and get notified when the data changes.
     */
    public LiveData<List<ProductCategoryEntity>> getProductCategory() {
        return mObservableProductCategory;
    }

    public LiveData<ProductCategoryEntity> loadCategoryByIdLive(final int categoryId) {
        return shopDatabase.productCategoryDao().loadCategoryByIdLive(categoryId);
    }

    public LiveData<List<ProductEntity>> loadProductsByCategoryId(final int categoryId){
        return shopDatabase.productDao().loadAllByCategoryId(categoryId);
    }

    public LiveData<List<ProductCommentEntity>> loadCommentsByProductId(final int productId) {
        return shopDatabase.productCommentDao().loadCommentsByProductId(productId);
    }

}
