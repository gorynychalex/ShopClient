package ru.popovich.shopclient.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;


import java.util.List;

import ru.popovich.shopclient.AppExecutors;
import ru.popovich.shopclient.data.DBDataGenerator;
import ru.popovich.shopclient.db.converter.DateConverter;
import ru.popovich.shopclient.db.dao.ProductCategoryDao;
import ru.popovich.shopclient.db.dao.ProductCommentDao;
import ru.popovich.shopclient.db.dao.ProductDao;
import ru.popovich.shopclient.db.entity.ProductCommentEntity;
import ru.popovich.shopclient.db.entity.ProductEntity;
import ru.popovich.shopclient.db.entity.ProductCategoryEntity;

/**
 * ProductEntity database
 */

/** exportSchema = false
 * if true: Warning: Schema export directory is not provided to the annotation processor so we cannot export the schema. You can either provide `room.schemaLocation` annotation processor argument OR set exportSchema to false.
 *
 * https://stackoverflow.com/questions/44322178/room-schema-export-directory-is-not-provided-to-the-annotation-processor-so-we
*/

@Database(entities = {ProductEntity.class, ProductCategoryEntity.class, ProductCommentEntity.class},
        version = 2,
        exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class ShopDatabase extends RoomDatabase {

    private static ShopDatabase shopDatabaseInstance;

    //TEST
    @VisibleForTesting
    public static final String DATABASE_NAME = "basic-sample-db";

    public abstract ProductCategoryDao productCategoryDao();
    public abstract ProductDao productDao();
    public abstract ProductCommentDao productCommentDao();

    private final MutableLiveData<Boolean> mutableLiveDataIsCreated = new MutableLiveData<>();

    public static ShopDatabase getShopDatabaseInstance(final Context context, final AppExecutors appExecutors){
        if(shopDatabaseInstance == null){
            synchronized (ShopDatabase.class){
                if (shopDatabaseInstance == null){
                    shopDatabaseInstance = buildDatabase(context.getApplicationContext(), appExecutors);
                    shopDatabaseInstance.updateDBCreated(context.getApplicationContext());
                }
            }
        }
        return shopDatabaseInstance;
    }

    /**
     * Build the database. {@link Builder#build()} only sets up the database configuration and
     * creates a new instance of the database.
     * The SQLite database is only created when it's accessed for the first time.
     */
    private static ShopDatabase buildDatabase(final Context appContext,
                                             final AppExecutors executors) {
        return Room.databaseBuilder(appContext, ShopDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        executors.diskIO().execute(() -> {

                            // Add a delay to simulate a long-running operation
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException ignored) {
                            }

                            // Generate the data for pre-population
                            ShopDatabase database = ShopDatabase.getShopDatabaseInstance(appContext, executors);

                            List<ProductCategoryEntity> productCategoryEntities = DBDataGenerator.getProductCategories();
                            List<ProductEntity> productEntities = DBDataGenerator.getProducts();
//                            List<ProductEntity> products = DataGenerator.generateProducts();
//                            List<ProductCommentEntity> comments =
//                                    DataGenerator.generateCommentsForProducts(products);

//                            insertData(database, productCategoryEntities, productEntities);
                            // notify that the database was created and it's ready to be used
                            database.setDatabaseCreated();
                        });
                    }
                }).build();
    }

    /**
     * Check whether the database already exists and expose it via {@link #getDBCreated()}
     */
    private void updateDBCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated(){
        mutableLiveDataIsCreated.postValue(true);
    }

    private static void insertData(final ShopDatabase shopDatabase,
                                   final List<ProductCategoryEntity> productCategories,
                                   final List<ProductEntity> products,
                                   final List<ProductCommentEntity> comments) {
        shopDatabase.runInTransaction(() -> {
            shopDatabase.productCategoryDao().insert(productCategories);
            shopDatabase.productDao().insert(products);
            shopDatabase.productCommentDao().insertAll(comments);
        });
    }

    public LiveData<Boolean> getDBCreated() {
        return mutableLiveDataIsCreated;
    }
}
