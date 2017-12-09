package ru.popovich.shopclient;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import ru.popovich.shopclient.data.BasketData;

public class BasketActivity extends Activity{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    TextView size_basket;
    TextView bill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        //Help addon ---------- DON'T SHOW ------------------------
        size_basket = (TextView) findViewById(R.id.size_basket);
        size_basket.setText((String.valueOf(BasketData.getBasket().getProducts().size())));
//        size_basket.setText(R.string.app_name);

        bill = (TextView) findViewById(R.id.bill);
        bill.setText(String.valueOf(BasketData.getBasket().getBill()));

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
        mAdapter = new AdapterRecyclerViewBasket(BasketData.getBasket());
        mRecyclerView.setAdapter(mAdapter);
    }
}
