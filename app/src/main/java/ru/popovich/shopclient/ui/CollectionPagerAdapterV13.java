package ru.popovich.shopclient.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import ru.popovich.shopclient.models.Basket;
import ru.popovich.shopclient.models.Catalog;

public class CollectionPagerAdapterV13 extends FragmentStatePagerAdapter {

    Catalog catalog;
    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    Basket basket;
    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public CollectionPagerAdapterV13(FragmentManager fm) {
        super(fm);
    }

    public CollectionPagerAdapterV13(FragmentManager fm, Catalog catalog, Basket basket) {
        super(fm); this.catalog = catalog; this.basket = basket;
    }


    @Override
    public Fragment getItem(int position) {
        CatalogFragment.ObjectFragment objectFragment = CatalogFragment.ObjectFragment.newInstance(position, catalog.getCategories().get(position).getProducts(), basket);
        return objectFragment;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return catalog.getCategories().get(position).getName();
    }

    @Override
    public int getCount() {
        return catalog.getCategories().size();
    }

}
