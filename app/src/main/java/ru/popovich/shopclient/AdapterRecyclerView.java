package ru.popovich.shopclient;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.util.ArraySet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.popovich.shopclient.data.BasketData;
import ru.popovich.shopclient.models.Basket;
import ru.popovich.shopclient.models.ModelProduct;
import ru.popovich.shopclient.models.ModelProductBasket;

/**
 * Created by gorynych on 01.11.17.
 */

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.ViewHolder> {

    private List<ModelProduct> mDataset;

    private Basket basket;

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    //if only basket
    public AdapterRecyclerView(List<ModelProduct> mDataset) {
        this.mDataset = mDataset;
    }

    public AdapterRecyclerView(List<ModelProduct> mDataset, Basket basket) {
        this.mDataset = mDataset;
        this.basket = basket;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = null;

        if(basket != null) {
            // create a new view
            v = (View) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cardview_layout, parent, false);
            // set the view's size, margins, paddings and layout parameters
        } else {
            v = (View) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cardview_basket, parent, false);
        }

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.textCategory.setText(mDataset.get(position).getOnCardText());
        holder.textProdname.setText(mDataset.get(position).getUnderCardText());
        holder.price.setText((String.valueOf(mDataset.get(position).getPrice())));
        holder.imageView.setImageResource(mDataset.get(position).getPictures());

        holder.buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.product_quantity.setText(String.valueOf(++holder.product_quantity_int));
                holder.price.setText((String.valueOf(holder.product_quantity_int * mDataset.get(position).getPrice())));
            }
        });
        holder.buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.product_quantity_int > 0)
                holder.product_quantity.setText(String.valueOf(--holder.product_quantity_int));
                holder.price.setText((String.valueOf(holder.product_quantity_int * mDataset.get(position).getPrice())));
            }
        });
//        holder.imageView.setImageResource(R.drawable.sandwich);

        //Press on the image basket
        holder.imageViewBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(basket.getProducts() == null){
                    basket.setProducts(new ArrayList<ModelProduct>());
                    basket.setCounterProduct(new ArrayList<Integer>());
                }

                if(basket.getProductBasketSet() == null){
                    basket.setProductBasketSet(new android.support.v4.util.ArraySet<ModelProductBasket>());
                    basket.setProductSet(new android.support.v4.util.ArraySet<ModelProduct>());
                }

                basket.getProducts().add(mDataset.get(position));
                basket.getCounterProduct().add(holder.product_quantity_int);

                basket.addProductBasketSet(mDataset.get(position),holder.product_quantity_int);
                basket.addProductMap(mDataset.get(position),holder.product_quantity_int);
//                basket.getProductBasketSet().add(new ModelProductBasket(mDataset.get(position),holder.product_quantity_int));
//                basket.getProductSet().add(new ModelProduct());



                Log.d("AdapterRecyclerView", "image basket on click");
                Log.d("AdapterRecyclerView", String.valueOf(basket.getProducts().size()));
//                basket.getProducts().add(mDataset.get(position));
//                holder.product_quantity_int;
            }
        });
    }
    @Override
    public int getItemCount() { return mDataset.size(); }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        final Context context;

        public TextView textCategory;
        public TextView textProdname;
        public TextView price;
        public ImageView imageView;
        public Button buttonPlus;
        public Button buttonMinus;
        public TextView product_quantity;
        public int product_quantity_int=1;
        public ImageView imageViewBasket;

        public ViewHolder(View itemView) {
            super(itemView);
            textProdname = itemView.findViewById(R.id.card_view_text_prodname);
            textCategory = itemView.findViewById(R.id.card_text_on_image);
            price = itemView.findViewById(R.id.price);
            imageView = itemView.findViewById(R.id.card_view_image);


            imageViewBasket = itemView.findViewById(R.id.imageViewBasket);

            product_quantity = itemView.findViewById(R.id.product_quantity);
            product_quantity.setText(String.valueOf(product_quantity_int));
            buttonPlus = itemView.findViewById(R.id.button_plus);
            buttonMinus = itemView.findViewById(R.id.button_minus);

            context = itemView.getContext();
        }
    }

}
