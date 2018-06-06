package ru.popovich.shopclient.ui;

/**
 * Example from
 * http://androiddhina.blogspot.ru/2017/11/how-to-use-google-map-in-fragment.html
 */

import android.Manifest;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import ru.popovich.shopclient.R;

public class MapsFragment extends android.support.v4.app.Fragment {

    private static final String TAG = "MapsFragment.class";

    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 1;
    private static final String KEY_LOCATION = "Vladivostok";
    private static final String KEY_CAMERA_POSITION = "Vladivostok";
    private static final double DEFAULT_LATITUDE = 43.16;
    private static final double DEFAULT_LONGTITUDE = 131.914;
    private static final int M_MAX_ENTRIES = 5;
    private final LatLng mDefaultLocation = new LatLng(DEFAULT_LATITUDE, DEFAULT_LONGTITUDE);

    // The entry points to the Places API.
//    private GeoDataClient mGeoDataClient;
//    private PlaceDetectionClient mPlaceDetectionClient;
    private GoogleApiClient mGoogleApiClient;

    private MapView mMapView;
    private GoogleMap mGoogleMap;
    private ArrayList<LatLng> markerPoints;


    private GeoDataClient mGeoDataClient;

    private PlaceLikelihoodBufferResponse mPlaceLikelihoodBufferResponse;
    private PlaceDetectionClient mPlaceDetectionClient;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private Location mLastKnownLocation;
    private boolean mLocationPermissionGranted;
    private CameraPosition mCameraPosition;
    private Parcelable mCurrentLocation;
    private String[] mLikelyPlaceName;    private String[] mLikelyPlaceAddress;    private String[] mLikelyPlaceAttributions;    private LatLng[] mLikelyPlaceLatLng;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.map_layout,container,false);

        mMapView = (MapView) v.findViewById(R.id.googleMapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();

        MapsInitializer.initialize(getActivity().getApplicationContext());

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mGoogleMap = googleMap;

                if (checkLocationPermission()) {
                    if (ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        mGoogleMap.setMyLocationEnabled(true);
                    }
                }

                markerPoints = new ArrayList<LatLng>();

                mGoogleMap.getUiSettings().setCompassEnabled(true);
                mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
                mGoogleMap.getUiSettings().setRotateGesturesEnabled(true);
                // For dropping a marker at a point on the Map
//                LatLng sydney = new LatLng(Float.parseFloat("Lat"),                                                                                     Float.parseFloat("Long"));
//                mGoogleMap.addMarker(new MarkerOptions().position(sydney).
//                        title("Title").snippet("TitleName"));
//                LatLng sydney = new LatLng(-34, 151);

                // For zooming automatically to the location of the marker
//                CameraPosition cameraPosition =                                                                           new CameraPosition.Builder().target(sydney).zoom(12).build();
//                mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition
//                        (cameraPosition ));

                mGoogleMap.addMarker(new MarkerOptions().position(mDefaultLocation).title("Marker in Vladivostok"));
                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(mDefaultLocation));
            }
        });

        return v;

    }

    private boolean checkLocationPermission() {
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
