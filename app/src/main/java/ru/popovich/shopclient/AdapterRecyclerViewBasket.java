package ru.popovich.shopclient;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import ru.popovich.shopclient.models.Basket;

/**
 * Created by gorynych on 27.11.17.
 */

public class AdapterRecyclerViewBasket extends RecyclerView.Adapter<AdapterRecyclerViewBasket.ViewHolder> {

    Basket basket;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        final Context context;

        public ViewHolder(View itemView) {
            super(itemView);

            context = itemView.getContext();
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
