package ru.popovich.shopclient;

import android.app.Application;
import android.arch.persistence.room.Room;

import ru.popovich.shopclient.db.ShopDatabase;
import ru.popovich.shopclient.db.SimpleProductDatabase;

public class SimpleApp extends Application {
    public static SimpleApp simpleAppInstance;
    private SimpleProductDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        simpleAppInstance = this;
        database = Room.databaseBuilder(this,SimpleProductDatabase.class,"simpleproducts").build();
    }

    public static SimpleApp getSimpleAppInstance(){ return simpleAppInstance; }
    public SimpleProductDatabase getDatabase(){ return database; }
}
