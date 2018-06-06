package ru.popovich.shopclient.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.icu.util.MeasureUnit;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.popovich.shopclient.MainActivity;
import ru.popovich.shopclient.R;
import ru.popovich.shopclient.SimpleApp;
import ru.popovich.shopclient.data.BasketData;
import ru.popovich.shopclient.data.CatalogData;
import ru.popovich.shopclient.data.DBDataGenerator;
import ru.popovich.shopclient.data.DBDataRepository;
import ru.popovich.shopclient.data.ShopDBUtilsProduct;
import ru.popovich.shopclient.db.ShopDatabase;
import ru.popovich.shopclient.db.SimpleProductDatabase;
import ru.popovich.shopclient.db.entity.ProductCategoryEntity;
import ru.popovich.shopclient.db.entity.ProductEntity;
import ru.popovich.shopclient.models.Basket;
import ru.popovich.shopclient.models.Catalog;
import ru.popovich.shopclient.models.ModelProduct;
import ru.popovich.shopclient.viewmodel.ProductViewModel;

/**
 * CATALOG AT FRAGMENT
 *
 *
 *
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CatalogFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CatalogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CatalogFragment extends android.support.v4.app.Fragment {

    public static final String TAG = "CatalogFragment.class";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Basket basket;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    private static CatalogFragment catalogFragment;

    private ViewDataBinding catalogFragmentBinding;

    public CatalogFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CatalogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CatalogFragment newInstance(String param1, String param2) {

        if(catalogFragment == null)
        catalogFragment = new CatalogFragment();
        Bundle args = new Bundle();
        if(param1 != null) args.putString(ARG_PARAM1, param1);
        if(param2 != null) args.putString(ARG_PARAM2, param2);
        if(param1 != null || param2 != null) catalogFragment.setArguments(args);
        return catalogFragment;
    }

    public static CatalogFragment newInstance() {
        if(catalogFragment == null)
            catalogFragment = new CatalogFragment();
        return catalogFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //If binding is define
//        catalogFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.catalog_viewpager, container, false);

//        catalogFragmentBinding.getRoot();

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.catalog_viewpager, container, false);

        //// CREATE DB Instance by First method with one param context
        ShopDatabase shopDatabase = ShopDatabase.getShopDatabaseInstance(getContext());

        //// INSERT PRODUCTS ////
//        ShopDatabase.insertProductsAndCategory(shopDatabase,
//                DBDataGenerator.getProductCategories(),
//                DBDataGenerator.getProducts());
//        ShopDatabase.insertProduct(shopDatabase, new ProductEntity(4,"Salmon",5F,1, MeasureUnit.KILOGRAM));

//        ShopDatabase.insertCatogory(shopDatabase, new ProductCategoryEntity("Fruit"));

//        shopDatabase.productDao().loadAllLive().observe(this, new Observer<List<ProductEntity>>() {
//            @Override
//            public void onChanged(@Nullable List<ProductEntity> productEntities) {
////                if(productEntities != null)
//                if(productEntities.size()>0)
//                Log.d(TAG, productEntities.get(productEntities.size()-1).getName() + " "
//                        + productEntities.get(productEntities.size()-1).getCategoryId());
//            }
//        });
//
//        shopDatabase.productCategoryDao().loadAll().observe(this, new Observer<List<ProductCategoryEntity>>() {
//            @Override
//            public void onChanged(@Nullable List<ProductCategoryEntity> productCategoryEntities) {
//
//                if(productCategoryEntities.size()>0)
//                Log.d(TAG, productCategoryEntities.get(productCategoryEntities.size()-1).getId() + ": " + productCategoryEntities.get(productCategoryEntities.size()-1).getName());
//            }
//        });

//        SimpleProductDatabase simpleProductDatabase = SimpleProductDatabase.getInstanseDB(getContext());

//        SimpleProductDatabase.insertProduct(new ProductEntity("Onion",1.0F,4.5F, MeasureUnit.KILOGRAM));

//        simpleProductDatabase.productDao().loadAllLive().observe(this, new Observer<List<ProductEntity>>() {
//            @Override
//            public void onChanged(@Nullable List<ProductEntity> productEntities) {
//                StringBuilder stringBuilder = new StringBuilder();
//                productEntities.forEach(x->stringBuilder.append(x.getName() + " "));
//                if(stringBuilder.length() != 0)
//                    Log.d(TAG, "Products in database: " + stringBuilder.toString());
//                else
//                    Log.d(TAG,"Database is empty!");
//            }
//        });

        // CATALOG DEFINE
        CatalogData.setCatalogs();

        ////// LIVE DATA ///// DEVELOPMENT /////
        LiveData<Catalog> liveData = CatalogData.getCatalogs();

        liveData.observe(this, new Observer<Catalog>() {
            @Override
            public void onChanged(@Nullable Catalog catalog) {

                Log.d(TAG, "catalog test:" + catalog.getCategories().get(0).getProducts().get(0).getOnCardText());

            }
        });

        Log.d(TAG,"liveData.hasActiveObservers(): " + liveData.hasActiveObservers());
        Log.d(TAG,"liveData.hasObservers(): " + liveData.hasObservers());



        ///////////////// BASKET INITIALIZE //////////////////////////////
        basket = new Basket();

        //////////////// TAB LAYOUTS ///////////////////////////////
        tabLayout = v.findViewById(R.id.catalog_tablayout);
        viewPager = v.findViewById(R.id.catalog_viewpager);

//        FragmentManager fragmentManager = getFragmentManager();
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //////////// Tab Layout /////////////////////
        pagerAdapter = new CollectionPagerAdapter(fragmentManager, CatalogData.getCatalogs(), BasketData.getBasket(), liveData);

//        pagerAdapter.setProducts(products2);
//        pagerAdapter.setCatalog(CatalogData.getCatalogs());
//        pagerAdapter.setBasket(BasketData.getBasket());

        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        fragmentTransaction.commit();

        return v;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public static class ObjectFragment extends Fragment {

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

            Bundle arg = getArguments();
            int pageNum = arg.getInt(ARG_OBJECT);

            View rootView = inflater.inflate(
                    R.layout.content_main, container, false);

            //Recycler view
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
