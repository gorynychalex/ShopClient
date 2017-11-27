package ru.popovich.shopclient;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import ru.popovich.shopclient.data.BasketData;
import ru.popovich.shopclient.models.Basket;

public class BasketActivity extends Activity{

    TextView productsBasket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        productsBasket = (TextView) findViewById(R.id.size_basket);

        productsBasket.setText(BasketData.getBasket().toString());
    }
}
