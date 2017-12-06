package ru.popovich.shopclient;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import ru.popovich.shopclient.data.BasketData;
import ru.popovich.shopclient.models.Basket;

public class BasketActivity extends Activity{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    TextView productsBasket;
    TextView commonPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        //Help addon ---------- DON'T SHOW ------------------------
        productsBasket = (TextView) findViewById(R.id.size_basket);
        productsBasket.setText(BasketData.getBasket().toString());
//        productsBasket.setText(R.string.app_name);

        commonPrice = (TextView) findViewById(R.id.common_price);

        //Recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_basket);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
//            mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
//        mAdapter = new AdapterRecyclerView(BasketData.getBasket().getProducts());
        mAdapter = new AdapterRecyclerViewBasket(BasketData.getBasket().getProductMap());
        mRecyclerView.setAdapter(mAdapter);

        commonPrice.setText(String.valueOf(BasketData.getBasket().getSum()));

    }
}
