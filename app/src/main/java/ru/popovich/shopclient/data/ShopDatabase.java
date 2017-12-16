package ru.popovich.shopclient.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.popovich.shopclient.dao.ProductDao;
import ru.popovich.shopclient.models.Product;

/**
 * Product database
 */

/** exportSchema = false
 * if true: Warning: Schema export directory is not provided to the annotation processor so we cannot export the schema. You can either provide `room.schemaLocation` annotation processor argument OR set exportSchema to false.
 *
 * https://stackoverflow.com/questions/44322178/room-schema-export-directory-is-not-provided-to-the-annotation-processor-so-we
*/
@Database(entities = {Product.class}, version = 1, exportSchema = false)
public abstract class ShopDatabase extends RoomDatabase {

    public abstract ProductDao productDao();
}
