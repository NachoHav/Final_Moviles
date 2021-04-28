package com.example.plantilla.ui.ui.perfil;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.plantilla.R;
import com.example.plantilla.modelo.Propietario;
import com.example.plantilla.request.ApiClient;

public class PerfilFragment extends Fragment
{
    private PerfilViewModel perfilViewModel;
    private EditText etDni, etNombre, etApellido, etEmail, etTelefono, etPassword;
    private Button btGuardar;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        inicializarComponentes(root);

        perfilViewModel.getPropietarioMutable().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                etDni.setText(propietario.getDni().toString()) ;
                etNombre.setText(propietario.getNombre().toString());
                etApellido.setText(propietario.getApellido().toString());
                etEmail.setText(propietario.getEmail().toString());
                etTelefono.setText(propietario.getTelefono().toString());
                etPassword.setText(propietario.getContraseña().toString());
            }
        });
        perfilViewModel.getBotonEditarMutable().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String textoBoton) {
                habilitar();
            }
        });
        perfilViewModel.getBotonGuardarMutable().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String textoBoton) {
                deshabilitar();
                Toast.makeText(getContext(), "Los datos fueron actualizados.", Toast.LENGTH_LONG).show();
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
        btGuardar = root.findViewById(R.id.btGuardar);

        deshabilitar();


        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Propietario p = new Propietario();

                p.setApellido(etApellido.getText().toString());
                p.setNombre(etNombre.getText().toString());
                p.setContraseña(etPassword.getText().toString());
                p.setDni(Long.parseLong(etDni.getText().toString()));
                p.setEmail(etEmail.getText().toString());
                p.setTelefono(etTelefono.getText().toString());

                perfilViewModel.HabDes(btGuardar.getText().toString(), p);

            }
        });

    }

    public void habilitar() {
        etDni.setEnabled(true);
        etTelefono.setEnabled(true);
        etEmail.setEnabled(true);
        etPassword.setEnabled(true);
        etNombre.setEnabled(true);
        etApellido.setEnabled(true);
        btGuardar.setText("Guardar");
    }
    public void deshabilitar() {
        etDni.setEnabled(false);
        etTelefono.setEnabled(false);
        etEmail.setEnabled(false);
        etPassword.setEnabled(false);
        etNombre.setEnabled(false);
        etApellido.setEnabled(false);
        btGuardar.setText("Editar");
    }

}