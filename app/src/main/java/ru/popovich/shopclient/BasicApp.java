package ru.popovich.shopclient;

import android.app.Application;

import ru.popovich.shopclient.data.DBDataRepository;
import ru.popovich.shopclient.db.ShopDatabase;

/**
 * Android Application class. Used for accessing singletons.
 */

public class BasicApp extends Application {
    private AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppExecutors = new AppExecutors();
    }

    public ShopDatabase getDatabase() {
        return ShopDatabase.getShopDatabaseInstance(this, mAppExecutors);
    }

    public DBDataRepository getRepository() {
        return DBDataRepository.getInstance(getDatabase());
    }
}
