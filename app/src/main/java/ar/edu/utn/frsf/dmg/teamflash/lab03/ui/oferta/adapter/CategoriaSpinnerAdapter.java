package ar.edu.utn.frsf.dmg.teamflash.lab03.ui.oferta.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ar.edu.utn.frsf.dmg.teamflash.lab03.R;
import ar.edu.utn.frsf.dmg.teamflash.lab03.model.Categoria;

/**
 * Created by AdminUser on 15/09/2016.
 */
public class CategoriaSpinnerAdapter extends ArrayAdapter<Categoria> {

    private Context context;

    public CategoriaSpinnerAdapter(Context context, List<Categoria> categorias) {
        super(context, R.layout.spinner_list_item, categorias);
        this.context= context;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        final int position= i;
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView= inflater.inflate(R.layout.spinner_list_item, null);

            viewHolder.nombre= (TextView) convertView.findViewById(R.id.textView_list_item_nombre);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.nombre.setText(getItem(position).getDescripcion().toString());

        return convertView;
    }

    //gestiona la lista usando el View Holder Pattern. Equivale a la típica implementación del getView
    //de un Adapter de un ListView ordinario
    @Override
    public View getDropDownView(int i, View convertView, ViewGroup parent) {
        final int position= i;
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView= inflater.inflate(R.layout.spinner_list_item, null);

            viewHolder.nombre= (TextView) convertView.findViewById(R.id.textView_list_item_nombre);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.nombre.setText(getItem(position).getDescripcion().toString());

        return convertView;
    }



    static class ViewHolder {
        protected TextView nombre;
    }

}
