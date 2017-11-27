package ru.popovich.shopclient.data;

import ru.popovich.shopclient.models.Basket;

/**
 * Created by gorynych on 27.11.17.
 */

public class BasketData {

    private static Basket basket;

    private BasketData(){}

    public static Basket getBasket(){
        if(basket == null){
            basket = new Basket();
        }
        return basket;
    }
}
