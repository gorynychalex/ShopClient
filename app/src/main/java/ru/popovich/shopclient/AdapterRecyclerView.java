package ru.popovich.shopclient;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.popovich.shopclient.models.ModelProduct;

/**
 * Created by gorynych on 01.11.17.
 */

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.ViewHolder> {

    private List<ModelProduct> mDataset;

    public AdapterRecyclerView(List<ModelProduct> mDataset) {
        this.mDataset = mDataset;
    }

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

        public ViewHolder(View itemView) {
            super(itemView);
            textProdname = itemView.findViewById(R.id.card_view_text_prodname);
            textCategory = itemView.findViewById(R.id.card_text_on_image);
            price = itemView.findViewById(R.id.price);
            imageView = itemView.findViewById(R.id.card_view_image);

            product_quantity = itemView.findViewById(R.id.product_quantity);
            product_quantity.setText(String.valueOf(product_quantity_int));
            buttonPlus = itemView.findViewById(R.id.button_plus);
            buttonMinus = itemView.findViewById(R.id.button_minus);



            context = itemView.getContext();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.textCategory.setText(mDataset.get(position).getOnCardText());
        holder.textProdname.setText(mDataset.get(position).getUnderCardText());
        holder.price.setText(mDataset.get(position).getPrice());
        holder.imageView.setImageResource(mDataset.get(position).getPictures());

        holder.buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.product_quantity.setText(String.valueOf(++holder.product_quantity_int));
            }
        });
        holder.buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.product_quantity_int > 0)
                holder.product_quantity.setText(String.valueOf(--holder.product_quantity_int));
            }
        });
//        holder.imageView.setImageResource(R.drawable.sandwich);

    }

    @Override
    public int getItemCount() { return mDataset.size(); }

}
