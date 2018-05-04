package ru.popovich.shopclient.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.popovich.shopclient.MainActivity;
import ru.popovich.shopclient.R;
import ru.popovich.shopclient.data.BasketData;
import ru.popovich.shopclient.data.CatalogData;
import ru.popovich.shopclient.models.Basket;
import ru.popovich.shopclient.models.ModelProduct;

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
public class CatalogFragment extends Fragment {
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
    private FragmentStatePagerAdapter pagerAdapter;

    private static CatalogFragment catalogFragment;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.catalog_viewpager, container, false);

        // CATALOG DEFINE
        CatalogData.setCatalogs();

        ///////////////// BASKET INITIALIZE //////////////////////////////
        basket = new Basket();

        //////////////// TAB LAYOUTS ///////////////////////////////
        tabLayout = v.findViewById(R.id.catalog_tablayout);
        viewPager = v.findViewById(R.id.catalog_viewpager);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //////////// Tab Layout /////////////////////
        pagerAdapter = new CollectionPagerAdapterV13(fragmentManager, CatalogData.getCatalogs(), BasketData.getBasket());
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
        }
    }
}
