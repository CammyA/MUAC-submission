package android.hmkcode.com.MUAC_CW_Cameron_Anderson;

import android.Manifest;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    // variables
    private GoogleMap mMap;
    private static final LatLng EKHome = new LatLng(55.7591402,-4.1883331);
    FragmentManager fmAboutFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        fmAboutFragment = this.getFragmentManager();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setRotateGesturesEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(EKHome,12));

        // create a new marker for the bus
        LatLng bus1 = new LatLng(55.766782, -4.145669);
        mMap.addMarker(new MarkerOptions().position(bus1).title("Bus 21 & 6").snippet("6 - To Glasgow // 21 - To Gardenhall"));

        // create a new marker for the bus
        LatLng bus2 = new LatLng(55.767186, -4.145341);
        mMap.addMarker(new MarkerOptions().position(bus2).title("Bus 21 & 6").snippet("6 - To Calderwood // 21 - To Glasgow"));

        // create a new marker for the bus
        LatLng bus3 = new LatLng(55.765714, -4.148060);
        mMap.addMarker(new MarkerOptions().position(bus3).title("Bus 21 & 6").snippet("6 - To Calderwood // 21 - To Glasgow"));

        // create a new marker for the bus
        LatLng bus4 = new LatLng(55.765131, -4.149385);
        mMap.addMarker(new MarkerOptions().position(bus4).title("Bus 21 & 6").snippet("6 - To Glasgow // 21 - To Gardenhall"));

        // create a new marker for the bus
        LatLng bus5 = new LatLng(55.768991, -4.143104);
        mMap.addMarker(new MarkerOptions().position(bus5).title("Bus 21 & 6").snippet("6 - To Calderwood // 21 - To Glasgow"));

        // create a new marker for the bus
        LatLng bus6 = new LatLng(55.768985, -4.143099);
        mMap.addMarker(new MarkerOptions().position(bus6).title("Bus 21 & 6").snippet("6 - To Glasgow 21 - To Gardenhall"));

        // use these values in the database
        // database could not be done in time

        //55.766782, -4.145669
        //55.767186, -4.145341

        //55.765714, -4.148060
        //55.765131, -4.149385

        //55.768991, -4.143104
        //55.768985, -4.143099

        // will ask for the users permission to use their gps location,
        // if accepted the location will be set to users gps position
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        else
        {
            //go to the users location
            mMap.setMyLocationEnabled(true);
        }
    }


    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    // dialog fragment
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.about:
                DialogFragment mcAboutFragment = new mcAboutFragment();
                mcAboutFragment.show(fmAboutFragment,"menu");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}