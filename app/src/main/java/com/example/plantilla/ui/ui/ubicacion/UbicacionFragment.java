package com.example.plantilla.ui.ui.ubicacion;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.plantilla.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class UbicacionFragment extends Fragment {

    private UbicacionViewModel ubicacionViewModel;
    private GoogleMap mapa;
    private MapView mapView;
    private static final LatLng INMOBILIARIA = new LatLng(37.239612077095906, -115.81207967660222);

  /*  private class MapaActual implements OnMapReadyCallback {

        @Override
        public void onMapReady(GoogleMap googleMap) {
            mapa = googleMap;
            mapa.addMarker(new MarkerOptions().position(INMOBILIARIA)).setTitle("Inmobiliaria Avengers");
            CameraPosition camPos = new CameraPosition.Builder().target(INMOBILIARIA).zoom(19).bearing(42).tilt(70).build();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(camPos);

            mapa.animateCamera(cameraUpdate);
        }
    }*/

   /* @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa)).getMapAsync( new MapaActual());
    }*/

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ubicacionViewModel =
                new ViewModelProvider(this).get(UbicacionViewModel.class);
        View root = inflater.inflate(R.layout.fragment_ubicacion, container, false);
        mapView = root.findViewById(R.id.mapa);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mapa = mMap;
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.

                    return;
                }
                mapa.setMyLocationEnabled(true);
                //To add marker
                mapa.addMarker(new MarkerOptions().position(INMOBILIARIA)).setTitle("Inmobiliaria Avengers");
                CameraPosition camPos = new CameraPosition.Builder().target(INMOBILIARIA).zoom(19).bearing(42).tilt(70).build();
                CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(camPos);
                mapa.animateCamera(cameraUpdate);

            }
        });
                return root;
    }
}