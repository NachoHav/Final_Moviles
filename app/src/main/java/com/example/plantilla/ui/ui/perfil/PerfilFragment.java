package com.example.plantilla.ui.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.plantilla.R;
import com.example.plantilla.modelo.Propietario;

public class PerfilFragment extends Fragment
{

    private PerfilViewModel perfilViewModel;
    EditText etDni, etNombre, etApellido, etEmail, etTelefono, etPassword;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        inicializarComponentes(root);

        perfilViewModel.getPropietarioMutable().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                etDni.setText(propietario.getDni().toString());
                etNombre.setText(propietario.getNombre().toString());
                etApellido.setText(propietario.getApellido().toString());
                etEmail.setText(propietario.getEmail().toString());
                etTelefono.setText(propietario.getTelefono().toString());
                etPassword.setText(propietario.getContraseña().toString());
            }
        });

        perfilViewModel.cargarPerfil();

        return root;
    }

    private void inicializarComponentes(View root) {
        etDni = root.findViewById(R.id.etDni);
        etNombre = root.findViewById(R.id.etNombre);
        etApellido = root.findViewById(R.id.etApellido);
        etEmail = root.findViewById(R.id.etEmail);
        etTelefono = root.findViewById(R.id.etTelefono);
        etPassword = root.findViewById(R.id.etPassword);
    }
}