package ru.popovich.shopclient;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import ru.popovich.shopclient.models.Basket;
import ru.popovich.shopclient.models.ModelProduct;
import ru.popovich.shopclient.models.ModelProductBasket;

/**
 * Created by gorynych on 27.11.17.
 */

public class AdapterRecyclerViewBasket extends RecyclerView.Adapter<AdapterRecyclerViewBasket.ViewHolder> {

//    private List<ModelProduct> mDataset;
    private Map<ModelProduct,ModelProductBasket> mDataMap;

    public AdapterRecyclerViewBasket(Map<ModelProduct, ModelProductBasket> mDataMap) {
        this.mDataMap = mDataMap;
    }

//    public AdapterRecyclerViewBasket(List<ModelProduct> mDataset) {
//        this.mDataset = mDataset;
//    }

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
        public TextView commonprice;

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
            commonprice = itemView.findViewById(R.id.common_price);
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

//        holder.textCategory.setText(mDataset.get(position).getOnCardText());
        holder.textCategory.setText(mDataMap.get(position).getProduct().getOnCardText());

//        holder.textProdname.setText(mDataset.get(position).getUnderCardText());
        holder.textProdname.setText(mDataMap.get(position).getProduct().getUnderCardText());


//        holder.price.setText((String.valueOf(mDataset.get(position).getPrice())));
        holder.price.setText((String.valueOf(mDataMap.get(position).getProduct().getPrice())));


//        holder.imageView.setImageResource(mDataset.get(position).getPictures());
        holder.imageView.setImageResource(mDataMap.get(position).getProduct().getPictures());

        holder.buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.product_quantity.setText(String.valueOf(++holder.product_quantity_int));
                holder.price.setText((String.valueOf(holder.product_quantity_int * mDataMap.get(position).getProduct().getPrice())));
            }
        });
        holder.buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.product_quantity_int > 0)
                    holder.product_quantity.setText(String.valueOf(--holder.product_quantity_int));
                holder.price.setText((String.valueOf(holder.product_quantity_int * mDataMap.get(position).getProduct().getPrice())));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataMap.size();
    }

}
