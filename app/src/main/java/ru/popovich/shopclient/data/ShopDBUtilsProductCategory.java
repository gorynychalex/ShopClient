package ru.popovich.shopclient.data;

import android.os.AsyncTask;

import java.util.List;

import ru.popovich.shopclient.db.ShopDatabase;
import ru.popovich.shopclient.db.entity.ProductCategoryEntity;

/**
 * https://medium.com/@magdamiu/android-room-persistence-library-97ad0d25668e
 */

public class ShopDBUtilsProductCategory {

    private static ShopDBTaskCategory shopDBTaskCategory;

    public static void addCategory(final ShopDatabase database, final ProductCategoryEntity category){
        shopDBTaskCategory = new ShopDBTaskCategory(database);
        shopDBTaskCategory.execute(category);
    }

    public static List<ProductCategoryEntity> getCategory(final ShopDatabase database){
        final ProductCategoryEntity category = new ProductCategoryEntity();
        shopDBTaskCategory = new ShopDBTaskCategory(database,0);
        shopDBTaskCategory.execute(category);
        return null;
    }


    public static List<ProductCategoryEntity> getCategory(final ShopDatabase database, final ProductCategoryEntity category){
        shopDBTaskCategory = new ShopDBTaskCategory(database,0);
        shopDBTaskCategory.execute(category);
        return null;
    }

    public static class ShopDBTaskCategory extends AsyncTask<ProductCategoryEntity, Void, List<ProductCategoryEntity>> {

        private final String TAG = "ShopDBTaskCategory";

        private final ShopDatabase db;
        private int operation;

        public ShopDBTaskCategory(ShopDatabase db) {
            this.db = db;
        }

        public ShopDBTaskCategory(ShopDatabase db, int operation) {
            this.db = db;
            this.operation = operation;
        }

        @Override
        protected List<ProductCategoryEntity> doInBackground(ProductCategoryEntity... productCategories) {

            switch (operation) {
                case 0:
                    db.productCategoryDao().loadAll();
                case 1:
                    db.productCategoryDao().insert(productCategories);
                    break;
                default:
                    break;
            }
            return null;
        }
    }
}
