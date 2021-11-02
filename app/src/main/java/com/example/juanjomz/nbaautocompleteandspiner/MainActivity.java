package com.example.juanjomz.nbaautocompleteandspiner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Equipo> listaEquipos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        llenarListaEquipos();
        AutoCompleteTextView editText=findViewById(R.id.actvEquipos);
        AutocomleteEquiposAdapter adapter=new AutocomleteEquiposAdapter(this,listaEquipos);
        editText.setAdapter(adapter);
    }


    private void llenarListaEquipos(){
        listaEquipos=new ArrayList<>();
        listaEquipos.add(new Equipo("Clippers",R.drawable.clippers));
        listaEquipos.add(new Equipo("Bulls",R.drawable.bulls));
        listaEquipos.add(new Equipo("Celtics",R.drawable.celtics));
        listaEquipos.add(new Equipo("GoldenState", R.drawable.goldenstate));
        listaEquipos.add(new Equipo("Knicks",R.drawable.knicks));
        listaEquipos.add(new Equipo("Lakers",R.drawable.lakers));
        listaEquipos.add(new Equipo("Nets",R.drawable.nets));
    }
    public class AutocomleteEquiposAdapter extends ArrayAdapter<Equipo>{
    private  List<Equipo> listaEquiposAdaptador;

        public AutocomleteEquiposAdapter(@NonNull Context context, @NonNull List<Equipo> objects) {
            super(context,0, objects);
            listaEquiposAdaptador=new ArrayList<>(listaEquipos);
        }

        @NonNull
        @Override
        public Filter getFilter() {
            return filtroDeEquipos;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            TextView textView=null;
            ImageView imageView=null;
            Equipo equipo=null;
            equipo=getItem(position);
            if(convertView==null){
                convertView=LayoutInflater.from(getContext()).inflate(
                        R.layout.autocomplete_row,parent,false
                );
            }
            textView=convertView.findViewById(R.id.tvNombreEquipo);
            imageView=convertView.findViewById(R.id.ivImagenEquipo);
            if(convertView!=null){
                textView.setText(equipo.getNombre());
                imageView.setImageResource(equipo.getIdFoto());
            }

            return convertView;
        }

        private Filter filtroDeEquipos=new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults resultado=new FilterResults();
                List<Equipo> sugerencia=new ArrayList<>();
                String patronDeFiltrado;
                if(charSequence == null || charSequence.length()<0){
                    sugerencia.addAll(listaEquiposAdaptador);
                }else{
                    patronDeFiltrado=charSequence.toString().toLowerCase().trim();
                    for(Equipo equipo:listaEquiposAdaptador){
                        if(equipo.getNombre().toLowerCase().contains(patronDeFiltrado)){
                            sugerencia.add(equipo);
                        }
                    }
                }
                resultado.values=sugerencia;
                resultado.count=sugerencia.size();
                return resultado;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                clear();
                addAll((List)filterResults.values);
                notifyDataSetChanged();
            }

            @Override
            public CharSequence convertResultToString(Object resultValue) {
                return super.convertResultToString(((Equipo)resultValue).getNombre());
            }
        };

    }

}