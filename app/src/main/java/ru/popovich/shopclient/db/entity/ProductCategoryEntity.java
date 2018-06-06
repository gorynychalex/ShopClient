package ru.popovich.shopclient.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

import ru.popovich.shopclient.models.ProductCategoryIf;

/**
 * Product Category Entity
 * @fields
 * id, name, description
 */

@Entity(tableName = "product_category")
public class ProductCategoryEntity implements ProductCategoryIf{

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String description;

    @ColumnInfo(name = "picture_url")
    private String pictureUrl;

    public ProductCategoryEntity() {
    }

    @Ignore
    public ProductCategoryEntity(String name) {
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getPictureUrl() {
        return null;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
