package com.example.plantilla.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.plantilla.R;

public class MainActivity extends AppCompatActivity {
    private EditText etUsuario, etContrasena;
    private Button btnLogin;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        inicializarComponentes();
        mainActivityViewModel.getMensajeMutable().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String mensaje) {
                //Dialogo

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Adventencia")
                        .setMessage(mensaje)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Intent i = new Intent(getApplicationContext(), SegundaActividad.class);
                                //startActivity(i);
                            }
                        }).show();

                //Toast.makeText(getApplicationContext(), "Funcionando", Toast.LENGTH_LONG).show();

            }
        });

        mainActivityViewModel.getLoginMutable().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean logueado) {
                if (logueado) {
                    Intent i = new Intent(getApplicationContext(), SegundaActividad.class);
                    startActivity(i);
                }
            }
        });

    }

    private void inicializarComponentes() {
        etUsuario = findViewById(R.id.etUsuario);
        etContrasena = findViewById(R.id.etContrasena);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = etUsuario.getText().toString();
                String contrasena = etContrasena.getText().toString();

                mainActivityViewModel.verificarDatos(usuario, contrasena);
            }
        });
    }

}