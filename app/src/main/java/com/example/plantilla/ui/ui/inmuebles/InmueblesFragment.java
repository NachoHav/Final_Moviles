package com.example.plantilla.ui.ui.inmuebles;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.plantilla.R;
import com.example.plantilla.modelo.Inmueble;
import com.example.plantilla.request.ApiClient;
import com.example.plantilla.ui.ui.home.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class InmueblesFragment extends Fragment {
    private HomeViewModel homeViewModel;

    private List<Inmueble> listaInmuebles = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_inmuebles, container, false);

        obtenerInmuebles();
        generarView(inflater, root);

        return root;
    }

    private void obtenerInmuebles() {
        ApiClient api = ApiClient.getApi();
        listaInmuebles = api.obtnerPropiedades();
        for (Inmueble inmueble :listaInmuebles) {
            Log.d("msj", inmueble.getDireccion());
        }

    }

    private void generarView(LayoutInflater layoutInflater, View root) {
        ArrayAdapter<Inmueble> arrayAdapter = new InmuebleAdapter(getContext(), R.layout.item_inmueble, listaInmuebles, layoutInflater);
        ListView listView = root.findViewById(R.id.lvInmuebles);

        listView.setAdapter(arrayAdapter);
    }
}