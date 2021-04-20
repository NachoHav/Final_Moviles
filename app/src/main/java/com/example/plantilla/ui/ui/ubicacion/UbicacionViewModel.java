package com.example.plantilla.ui.ui.ubicacion;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UbicacionViewModel extends ViewModel
{

    private MutableLiveData<String> mText;

    public UbicacionViewModel()
    {
        mText = new MutableLiveData<>();
        mText.setValue("This is ubicacion fragment");
    }

    public LiveData<String> getText()
    {
        return mText;
    }
}