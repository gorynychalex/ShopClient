package ru.popovich.shopclient.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.popovich.shopclient.R;
import ru.popovich.shopclient.data.BasketData;

public class BasketFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    TextView size_basket;
    TextView bill;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_basket, container,false);

        //Help addon ---------- DON'T SHOW ------------------------
        size_basket = (TextView) v.findViewById(R.id.size_basket);
        size_basket.setText((String.valueOf(BasketData.getBasket().getProducts().size())));
//        size_basket.setText(R.string.app_name);

        bill = (TextView) v.findViewById(R.id.bill);
        bill.setText(String.valueOf(BasketData.getBasket().getBill()));

        //Recycler view
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_basket);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
//            mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
//        mAdapter = new AdapterRecyclerView(BasketData.getBasket().getProducts());
        mAdapter = new AdapterRecyclerViewBasket(BasketData.getBasket());
        mRecyclerView.setAdapter(mAdapter);

        return v;
    }
}
