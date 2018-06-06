package ru.popovich.shopclient.data;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import ru.popovich.shopclient.db.ShopDatabase;
import ru.popovich.shopclient.db.entity.ProductEntity;

/**
 * https://medium.com/@magdamiu/android-room-persistence-library-97ad0d25668e
 */

public class ShopDBUtilsProduct {


    public static void addProduct(final ShopDatabase database, final ProductEntity productEntity){
        new ShopDBTaskInsert(database).execute(productEntity);
    }

    public static List<ProductEntity> getProducts(final ShopDatabase database){
        return (List<ProductEntity>) new ShopDBTaskGet(database).execute();
    }


    // Async task for insert data operation
    public static class ShopDBTaskInsert extends AsyncTask<ProductEntity, Void, Void> {

        private final String TAG = "ShopDBTaskIsert";

        private final ShopDatabase db;


        public ShopDBTaskInsert(ShopDatabase db) {
                this.db = db;
        }

        @Override
        protected Void doInBackground(ProductEntity... productEntities) {
                db.productDao().insertAll(productEntities);
                Log.d(TAG, String.valueOf(db.productDao().loadAll().size()));
                return null;
        }
    }

    public static class ShopDBTaskGet extends AsyncTask<Void, Void, List<ProductEntity>> {

        private final String TAG = "ShopDBTaskGet";

        private final ShopDatabase db;


        public ShopDBTaskGet(ShopDatabase db) {
            this.db = db;
        }

        @Override
        protected List<ProductEntity> doInBackground(Void... voids) {
            List<ProductEntity> productEntities1 = db.productDao().loadAll();
            Log.d(TAG, String.valueOf(db.productDao().loadAll().size()));
            return productEntities1;
        }

        @Override
        protected void onPostExecute(List<ProductEntity> productEntities) {
            super.onPostExecute(productEntities);

        }
    }

}
