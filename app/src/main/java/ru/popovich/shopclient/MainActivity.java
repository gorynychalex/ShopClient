package ru.popovich.shopclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import ru.popovich.shopclient.data.BasketData;
import ru.popovich.shopclient.data.CatalogData;
import ru.popovich.shopclient.models.Basket;
import ru.popovich.shopclient.models.Catalog;
import ru.popovich.shopclient.models.ModelProduct;
import ru.popovich.shopclient.models.ProdCategory;

import static ru.popovich.shopclient.R.drawable.sandwich;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    CollectionPagerAdapter pagerAdapter;

    Basket basket;

    Intent intentBasket;

    /// Models
//    private Catalog catalogs = new Catalog();
//    private List<ProdCategory> prodCategories1 = new ArrayList<>();
//    private List<ProdCategory> prodCategories2 = new ArrayList<>();
//    private List<ProdCategory> prodCategories3 = new ArrayList<>();
//    private List<ModelProduct> products1 = new ArrayList<>();
//    private List<ModelProduct> products2 = new ArrayList<>();
//    private List<ModelProduct> products3 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CatalogData.setCatalogs();

        //////////////// CATALOG EXAMPLE //////////////////////////////
//        catalogs.setName("Drinks and Snaks");
        //Initialize Products
//        products1.add(new ModelProduct("Sandwich", "Rio De Janeiro",R.drawable.sandwich,2F));
//        products1.add(new ModelProduct("Hot dog", "Hot Evening", R.drawable.sandwich2, 2.5F));
//        products1.add(new ModelProduct("Sanwich", "Reptile", R.drawable.sandwich, 5F));
//
//        prodCategories1.add(new ProdCategory("Sandwichs",products1));
//
//        products2.add(new ModelProduct("Apple Juice", "Apple Juice", R.drawable.shwepps,2F));
//        products2.add(new ModelProduct("Orange Juice", "Orange Juice", R.drawable.ic_basket, 2.5F));
//        products2.add(new ModelProduct("Strawberry Juice", "Strawberry Juice", R.drawable.shwepps, 5F));
//
//        prodCategories1.add(new ProdCategory("Drinks", products2));
//
//        products3.add(new ModelProduct("Noodle", "Noodle", R.drawable.shwepps,2.0F));
//        products3.add(new ModelProduct("Chiken", "Chiken", R.drawable.sandwich, 2.5F));
//        products3.add(new ModelProduct("Nagins", "Nagins", R.drawable.ic_basket, 5F));
//
//
//        prodCategories1.add(new ProdCategory("Drinks", products3));
//
//        catalogs.setCategories(prodCategories1);


        ///////////////// BASKET INITIALIZE //////////////////////////////
        basket = new Basket();

        ////////////// Different toolbars and buttons ////////////////////
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        //////////// Tab Layout /////////////////////
        pagerAdapter = new CollectionPagerAdapter(getSupportFragmentManager());
//        pagerAdapter.setProducts(products2);
        pagerAdapter.setCatalog(CatalogData.getCatalogs());
        pagerAdapter.setBasket(BasketData.getBasket());

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);

//        tabLayout.setTabsFromPagerAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,
                        String.valueOf(BasketData.getBasket().getProducts() != null?
                                "There are " + BasketData.getBasket().getProducts().size() + " products in the basket." :
                                "Basket is empty."), Snackbar.LENGTH_LONG)
                        .setAction("Basket", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                intentBasket = new Intent(getApplicationContext(), BasketActivity.class);
                                startActivity(intentBasket);
                            }
                        }).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static class ObjectFragment extends Fragment{

        public static final String ARG_OBJECT = "object";

        private RecyclerView mRecyclerView;
        private RecyclerView.Adapter mAdapter;
        private RecyclerView.LayoutManager mLayoutManager;

        private List<ModelProduct> products = new ArrayList<>();

        private Basket basket;

        public ObjectFragment() {

        }

        public static ObjectFragment newInstance(int pageNumber, List<ModelProduct> products, Basket basket){
            ObjectFragment objectFragment = new ObjectFragment();
            objectFragment.products = products;
            objectFragment.basket = basket;
            Bundle arg = new Bundle();
            arg.putInt(ARG_OBJECT,pageNumber);
            objectFragment.setArguments(arg);
            return objectFragment;
        }


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//            return super.onCreateView(inflater, container, savedInstanceState);

            //Initialize Products
//            products1.add(new ModelProduct("Sandwich", "Rio De Janeiro", "@drawable/sandwich","2$"));
//            products1.add(new ModelProduct("Hot dog", "Hot Evening", "@drawable/sandwich", "2$ 50c"));
//            products1.add(new ModelProduct("Sanwich", "Reptile", "@drawable/sandwich", "5$"));


            Bundle arg = getArguments();
            int pageNum = arg.getInt(ARG_OBJECT);

            View rootView = inflater.inflate(
                    R.layout.content_main, container, false);

            //Recycler view
            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);

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
//            TextView textView = new TextView(getActivity());
//            textView.setText("Hello from fragment" + pageNum);
//            textView.setGravity(Gravity.CENTER);
//            return textView;
        }
    }
}
