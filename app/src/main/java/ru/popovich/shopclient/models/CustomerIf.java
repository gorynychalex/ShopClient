package ru.popovich.shopclient.models;

import java.util.List;

public interface CustomerIf {
    int getId();
    String getName();
//    List<String> getPhoneNumbers();
    String getDescription();
    String getPictureUrl();
}
