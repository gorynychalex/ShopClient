package ru.popovich.shopclient.models;

import java.util.Date;

/**
 * Comments for Product
 */

public interface ProductCommentIf {
    int getId();
    int getProductId();
    String getText();
    Date getPostedAt();
}
