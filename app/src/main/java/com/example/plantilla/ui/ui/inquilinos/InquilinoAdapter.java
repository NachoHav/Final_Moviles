package com.example.plantilla.ui.ui.inquilinos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.plantilla.R;
import com.example.plantilla.modelo.Inmueble;

import java.util.List;

public class InquilinoAdapter extends ArrayAdapter<Inmueble> {
    private Context context;
    private List<Inmueble> inmueblesAlquilados;
    private LayoutInflater layoutInflater;

    public InquilinoAdapter(@NonNull Context context, int resource, @NonNull List<Inmueble> objects, LayoutInflater layoutInflater) {
        super(context, resource, objects);
        this.context = context;
        this.inmueblesAlquilados = objects;
        this.layoutInflater = layoutInflater;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView =  convertView;

        if ( itemView==null) {
            itemView = layoutInflater.inflate(R.layout.item_inquilino, parent, false);
        }

        Inmueble inmueble = inmueblesAlquilados.get(position);

        ImageView fotoInmueble = itemView.findViewById(R.id.ivFotoInmuebleL);
        TextView tvDireccion = itemView.findViewById(R.id.tvDireccionInmuebleL);

        Glide.with(getContext())
                .load(inmueble.getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(fotoInmueble);

        tvDireccion.setText(inmueble.getDireccion());


        return itemView;
    }
}
