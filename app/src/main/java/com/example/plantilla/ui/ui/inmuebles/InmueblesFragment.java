package com.example.plantilla.ui.ui.inmuebles;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.plantilla.R;
import com.example.plantilla.modelo.Inmueble;

import java.util.List;

public class InmueblesFragment extends Fragment {

    private InmueblesViewModel inmueblesViewModel;
    private List<Inmueble> listaInmuebles;
    View root;

    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        inmueblesViewModel = new ViewModelProvider(this).get(InmueblesViewModel.class);
        root = inflater.inflate(R.layout.fragment_inmuebles, container, false);

        inmueblesViewModel.getListaInmuebleMutable().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                listaInmuebles = inmuebles;
                generarView(inflater, root);
            }
        });

        inmueblesViewModel.cargarInmuebles();

        return root;
    }

    private void generarView(LayoutInflater layoutInflater, View root) {
        ArrayAdapter<Inmueble> arrayAdapter = new InmuebleAdapter(getContext(), R.layout.item_inmueble, listaInmuebles, layoutInflater);
        ListView listView = root.findViewById(R.id.lvInmuebles);

        listView.setAdapter(arrayAdapter);
    }
}