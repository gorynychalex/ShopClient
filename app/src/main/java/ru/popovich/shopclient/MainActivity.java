package ru.popovich.shopclient;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.app.Fragment;
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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import ru.popovich.shopclient.data.BasketData;
import ru.popovich.shopclient.db.entity.ProductEntity;
import ru.popovich.shopclient.ui.BasketFragment;
import ru.popovich.shopclient.ui.CatalogFragment;
import ru.popovich.shopclient.ui.AdapterRecyclerView;
import ru.popovich.shopclient.ui.CollectionPagerAdapter;
import ru.popovich.shopclient.ui.MapsFragment;
import ru.popovich.shopclient.viewmodel.ProductListViewModel;
import ru.popovich.shopclient.db.ShopDatabase;
import ru.popovich.shopclient.models.Basket;
import ru.popovich.shopclient.models.ModelProduct;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, CatalogFragment.OnFragmentInteractionListener {


    private static final String TAG = "MainActivity";

    Toolbar toolbar;
    DrawerLayout drawer;

    NavigationView navigationView;

    FloatingActionButton fab;


    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    android.support.v4.app.Fragment fragmentSupportV4;
    android.support.v4.app.FragmentManager fragmentManagerSupportV4;
    android.support.v4.app.FragmentTransaction fragmentTransactionSupportV4;

    Basket basket;

    Intent intentBasket;
    ShopDatabase db;

    ProductListViewModel productListViewModel;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ///// START FRAGMENT ////
        setFragment(CatalogFragment.newInstance(),R.id.content_coord_frame);

        db = Room
                .databaseBuilder(getApplicationContext(), ShopDatabase.class, "database-shop")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        for(ProductEntity entity: db.productDao().loadAll())
            Log.d(TAG+"_DBTHREAD", entity.getName());

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            ShopDBUtilsProduct.addCategoty(db,new ProductCategoryEntity("Sandwich"));
//            ShopDBUtilsProduct.addProduct(db,new ProductEntity("Sandwich",2.5F,200, MeasureUnit.GRAM,1));
//        }

//        ShopDBUtilsProduct.getCategoty(db);

        //Get the viewmodel
        //productListViewModel = ViewModelProviders.of(this).get(ProductListViewModel.class);

        ////// Test: Create the observer with updates data
//        final Observer<ProductCategoryEntity> productCategoryObserver = new Observer<ProductCategoryEntity>() {
//            @Override
//            public void onChanged(@Nullable ProductCategoryEntity productCategory) {
//                Log.d("MainActivityObserver",productCategory.getName());
//            }
//        };

//        productListViewModel.getMutableLiveData().observe(this, productCategoryObserver);

        //// BEGIN OLD CATAloG ------------
//        CatalogData.setCatalogs();

        ///////////////// BASKET INITIALIZE //////////////////////////////
//        basket = new Basket();

        ////////////// MAIN toolbars and buttons ////////////////////
        toolbar = findViewById(R.id.toolbar1);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        fab = findViewById(R.id.fab);
        setSupportActionBar(toolbar);

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
                                setFragment(new BasketFragment(),R.id.content_coord_frame);
                            }
                        }).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


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

        if (id == R.id.nav_gallery) {

            setFragment(CatalogFragment.newInstance(),R.id.content_coord_frame);

        } else if (id == R.id.nav_map) {

            setFragment(new MapsFragment(),R.id.content_coord_frame);

        } else if (id == R.id.nav_slideshow) {

            setFragment(new BasketFragment(),R.id.content_coord_frame);

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setFragment(Fragment frmt, int id){
        fragmentTransaction = getFragmentManager().beginTransaction();
        if(fragment != null){
            fragment = frmt;
            fragmentTransaction.replace(R.id.content_coord_frame,fragment);
        } else {
            fragment = frmt;
            fragmentTransaction.add(R.id.content_coord_frame,fragment);
        }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
