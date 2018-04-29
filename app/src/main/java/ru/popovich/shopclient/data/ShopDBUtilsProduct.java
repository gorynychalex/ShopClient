package ru.popovich.shopclient.data;

import android.os.AsyncTask;
import android.util.Log;

import ru.popovich.shopclient.db.ShopDatabase;
import ru.popovich.shopclient.db.entity.ProductEntity;

/**
 * https://medium.com/@magdamiu/android-room-persistence-library-97ad0d25668e
 */

public class ShopDBUtilsProduct {

    private static ShopDBTask shopDBTask;

    public static void addProduct(final ShopDatabase database, final ProductEntity productEntity){
        shopDBTask = new ShopDBTask(database, 1);
        shopDBTask.execute(productEntity);
    }


    public static class ShopDBTask extends AsyncTask<ProductEntity, Void, Void> {

        private final String TAG = "ShopDBTask";

        private final ShopDatabase db;

        private int operation;

        public ShopDBTask(ShopDatabase db) {
                this.db = db;
        }

        public ShopDBTask(ShopDatabase db, int operation) {
            this.db = db;
            this.operation = operation;
        }

        @Override
        protected Void doInBackground(ProductEntity... productEntities) {
            switch (operation) {
                case 1:
                    db.productDao().insertAll(productEntities);
                    Log.d(TAG, String.valueOf(db.productDao().loadAll().size()));
                    break;
                default:
                    break;
            }
            return null;
        }
   }
}
