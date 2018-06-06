package ru.popovich.shopclient.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.icu.util.MeasureUnit;

import ru.popovich.shopclient.models.ProductIf;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * ProductEntity Entity class
 * @Fields
 * id, categoryId, name, description, price, measure, measureUnit, pictureUrl
 */

@Entity(tableName = "product",
        foreignKeys ={
            @ForeignKey(entity = ProductCategoryEntity.class,parentColumns = "id",
                        childColumns = "category_id",onDelete = CASCADE)},
        indices = {@Index(value = "category_id")} )
public class ProductEntity implements ProductIf {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "category_id")
    private int categoryId;

    private String name;

    private String description;

    @ColumnInfo(name = "picture_url")
    private String pictureUrl;

    private float price;

    private float measure;

    private String measureUnit;

    public ProductEntity() {
    }

    @Ignore
    public ProductEntity(String name, float price, float measure, MeasureUnit measureUnit) {
        this.name = name;
        this.price = price;
        this.measure = measure;
        this.measureUnit = measureUnit.toString();
        this.categoryId = 1;
    }

    @Ignore
    public ProductEntity(String name, float price, float measure) {
        this.name = name;
        this.price = price;
        this.measure = measure;
        this.measureUnit = "KILOGRAMM";
        this.categoryId = 1;
    }


    @Ignore
    public ProductEntity(int categoryId, String name, float price, float measure, MeasureUnit measureUnit) {
        this.name = name;
        this.price = price;
        this.measure = measure;
        this.measureUnit = measureUnit.toString();
        this.categoryId = categoryId;
    }

    @Ignore
    public ProductEntity(int categoryId, String name, float price, float measure) {
        this.name = name;
        this.price = price;
        this.measure = measure;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getMeasure() {
        return measure;
    }

    public void setMeasure(float measure) {
        this.measure = measure;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
