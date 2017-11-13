package ru.popovich.shopclient;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import ru.popovich.shopclient.models.Catalog;
import ru.popovich.shopclient.models.ModelProduct;

/**
 * Created by gorynych on 13.11.17.
 */

public class CollectionPagerAdapter extends FragmentStatePagerAdapter {

    String[] tab_names = {"Drink", "Snaks", "Sandwich"};

//    List<ModelProduct> products;

    Catalog catalog;

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public CollectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
//        Fragment fragment = new ObjectFragment();
//        Bundle args = new Bundle();
//        // Our object is just an integer :-P
//        args.putInt(ObjectFragment.ARG_OBJECT, position + 1);
//        fragment.setArguments(args);
//        return fragment;
//        return null;
        MainActivity.ObjectFragment objectFragment = MainActivity.ObjectFragment.newInstance(position, catalog.getCategories().get(position).getProducts());
        return objectFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tab_names[position];
    }

    @Override
    public int getCount() {
        return tab_names.length;
    }
}