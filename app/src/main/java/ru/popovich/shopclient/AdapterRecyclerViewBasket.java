package ru.popovich.shopclient;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.popovich.shopclient.models.Basket;
import ru.popovich.shopclient.models.ModelProductBasket;

/**
 * Adapter for Basket Recycler View
 */

public class AdapterRecyclerViewBasket extends RecyclerView.Adapter<AdapterRecyclerViewBasket.ViewHolder> {

    private List<ModelProductBasket> mDataset;
    private Basket basket;

    public AdapterRecyclerViewBasket(List<ModelProductBasket> mDataset) {
        this.mDataset = mDataset;
    }

    public AdapterRecyclerViewBasket(Basket basket) {
        this.basket = basket;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        final Context context;

        private TextView textCategory;
        private TextView textProdname;
        private TextView price;
        private ImageView imageView;
        private Button buttonPlus;
        private Button buttonMinus;
        private TextView product_quantity;
        private int product_quantity_int=1;
        private ImageView imageViewBasket;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();

            textProdname = itemView.findViewById(R.id.card_view_text_prodname);
            textCategory = itemView.findViewById(R.id.card_text_on_image);
            price = itemView.findViewById(R.id.price);
            imageView = itemView.findViewById(R.id.card_view_image);
            imageViewBasket = itemView.findViewById(R.id.imageViewBasket);

            product_quantity = itemView.findViewById(R.id.product_quantity);
            product_quantity.setText(String.valueOf(product_quantity_int));
            buttonPlus = itemView.findViewById(R.id.button_plus);
            buttonMinus = itemView.findViewById(R.id.button_minus);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_basket, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

//        holder.product_quantity_int = mDataset.get(position).getCount();
        holder.product_quantity_int = basket.getProducts().get(position).getCount();

        holder.product_quantity.setText(String.valueOf(holder.product_quantity_int));

        Log.d("AdapterRecyclerView", "position = " + position);
        Log.d("AdapterRecyclerView", "getOnCardText() = " + basket.getProducts().get(position).getProduct().getUnderCardText());

        holder.textCategory.setText(basket.getProducts().get(position).getProduct().getOnCardText());

        holder.textProdname.setText(basket.getProducts().get(position).getProduct().getUnderCardText());

        holder.price.setText((String.valueOf(basket.getProducts().get(position).getSumPrice())));

        holder.imageView.setImageResource(basket.getProducts().get(position).getProduct().getPictures());

        holder.buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.product_quantity.setText(String.valueOf(++holder.product_quantity_int));
                holder.price.setText((String.valueOf(holder.product_quantity_int * basket.getProducts().get(position).getProduct().getPrice())));
            }
        });

        holder.buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.product_quantity_int > 0)
                    holder.product_quantity.setText(String.valueOf(--holder.product_quantity_int));

                    holder.price.setText((String.valueOf(holder.product_quantity_int * basket.getProducts().get(position).getProduct().getPrice())));
            }
        });
    }

    @Override
    public int getItemCount() {
        return basket.getProducts().size();
    }

}
