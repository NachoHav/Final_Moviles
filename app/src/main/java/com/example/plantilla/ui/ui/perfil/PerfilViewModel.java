package com.example.plantilla.ui.ui.perfil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Propietario;
import com.example.plantilla.request.ApiClient;

public class PerfilViewModel extends ViewModel
{
    private MutableLiveData<Propietario> propietarioMutable;

    public LiveData<Propietario> getPropietarioMutable () {
        if ( propietarioMutable == null ) {
            propietarioMutable = new MutableLiveData<>();
        }
        return propietarioMutable;
    }

    public void cargarPerfil() {
        ApiClient api = ApiClient.getApi();
        Propietario propietario = api.obtenerUsuarioActual();
        propietarioMutable.setValue(propietario);
    }
}