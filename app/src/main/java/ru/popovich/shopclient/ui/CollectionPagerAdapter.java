package ru.popovich.shopclient.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.MutableContextWrapper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.popovich.shopclient.R;
import ru.popovich.shopclient.models.Basket;
import ru.popovich.shopclient.models.Catalog;
import ru.popovich.shopclient.models.ModelProduct;

/**
 * ViewPager content
 * define adapter for fragment to display some SECTIONS
 */

public class CollectionPagerAdapter extends FragmentStatePagerAdapter {

//    String[] tab_names = {"Drink", "Snaks", "Sandwich"};

//    List<ModelProduct> products;

    private LiveData<Catalog> catalogLiveData;

        Catalog catalog;
        public void setCatalog(Catalog catalog) {
            this.catalog = catalog;
        }

        Basket basket;
        public void setBasket(Basket basket) {
            this.basket = basket;
        }


        //// Setter for Catalog Live Data
    public void setCatalogLiveData(LiveData<Catalog> catalogLiveData) {
        this.catalogLiveData = catalogLiveData;
    }

    public CollectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public CollectionPagerAdapter(FragmentManager fm, Catalog catalog, Basket basket) {
        super(fm);
        this.catalog = catalog; this.basket = basket;
    }

    public CollectionPagerAdapter(FragmentManager fm, Catalog catalog, Basket basket, LiveData<Catalog> catalogLiveData) {
        super(fm);
        this.catalog = catalog; this.basket = basket;
        this.catalogLiveData = catalogLiveData;
    }


    @Override
    public Fragment getItem(int position) {
//        Fragment fragment = new ProductSectionFragment();
//        Bundle args = new Bundle();
//        // Our object is just an integer :-P
//        args.putInt(ProductSectionFragment.ARG_OBJECT, position + 1);
//        fragment.setArguments(args);
//        return fragment;
//        return null;
        ProductSectionFragment productSectionFragment =
                ProductSectionFragment.newInstance(
                        position,
                        catalog.getCategories().get(position).getProducts(),
                        basket);

//        ProductSectionFragment productSectionFragment = ProductSectionFragment.newInstance(position, catalogLiveData.getValue().getCategories().get(position), basket);
        return productSectionFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return catalog.getCategories().get(position).getName();
    }

    @Override
    public int getCount() {
        return catalog.getCategories().size();
    }


    /**
     * Static class
     * define content for every SECTION on ViewPager
     */

    public static class ProductSectionFragment extends Fragment {

        public static final String ARG_OBJECT = "object";


        private RecyclerView mRecyclerView;
        private RecyclerView.Adapter mAdapter;
        private RecyclerView.LayoutManager mLayoutManager;

        private List<ModelProduct> products = new ArrayList<>();

        private Basket basket;

        public ProductSectionFragment() {

        }

        public static ProductSectionFragment newInstance(int pageNumber, List<ModelProduct> products, Basket basket){
            ProductSectionFragment productSectionFragment = new ProductSectionFragment();
            productSectionFragment.products = products;
            productSectionFragment.basket = basket;
            Bundle arg = new Bundle();
            arg.putInt(ARG_OBJECT,pageNumber);
            productSectionFragment.setArguments(arg);
            return productSectionFragment;
        }


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            Bundle arg = getArguments();
            int pageNum = arg.getInt(ARG_OBJECT);

            View rootView = inflater.inflate(
                    R.layout.content_main, container, false);

            //Recycler view for every product SECTION
            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.section_recycler_view);

            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
//            mRecyclerView.setHasFixedSize(true);

            // use a linear layout manager
            mLayoutManager = new LinearLayoutManager(rootView.getContext());
            mRecyclerView.setLayoutManager(mLayoutManager);

            // specify an adapter (see also next example)
            mAdapter = new AdapterRecyclerView(products,basket);
            mRecyclerView.setAdapter(mAdapter);

            return rootView;
        }
    }
}