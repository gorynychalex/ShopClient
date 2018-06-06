package ru.popovich.shopclient.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

import ru.popovich.shopclient.models.CustomerIf;

import static android.arch.persistence.room.ForeignKey.CASCADE;


@Entity(tableName = "customer")
public class CustomerEntity implements CustomerIf {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

//    private List<String> phoneNumbers;
    private String description;
    private String pictureUrl;

    public CustomerEntity() {
    }

    @Ignore
    public CustomerEntity(String name) {
        this.name = name;
//        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

//    @Override
//    public List<String> getPhoneNumbers() {
//        return null;
//    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getPictureUrl() {
        return null;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public void setPhoneNumbers(List<String> phoneNumbers) {
//        this.phoneNumbers = phoneNumbers;
//    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
