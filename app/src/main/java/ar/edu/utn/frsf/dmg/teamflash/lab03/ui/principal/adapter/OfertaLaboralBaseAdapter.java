package ar.edu.utn.frsf.dmg.teamflash.lab03.ui.principal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import ar.edu.utn.frsf.dmg.teamflash.lab03.R;
import ar.edu.utn.frsf.dmg.teamflash.lab03.model.Trabajo;

/**
 * Created by AdminUser on 09/09/2016.
 */
public class OfertaLaboralBaseAdapter extends BaseAdapter {

    private OnItemLongClickListener onItemLongClickListener;
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
    private DecimalFormat f = new DecimalFormat("##.00");
    private Context context;
    private List<Trabajo> trabajos;
    private Integer tam;

    public OfertaLaboralBaseAdapter(Context context, List<Trabajo> trabajos) {
        this.context= context;
        this.trabajos= trabajos;
        this.tam= trabajos.size();
    }

    @Override
    public int getCount() {
        return tam;
    }

    @Override
    public Object getItem(int i) {
        return trabajos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        final int position= i;

        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);

            convertView = inflater.inflate(R.layout.oferta_list_item, viewGroup, false);

            holder = new ViewHolder();
            holder.linearLayout= (LinearLayout) convertView.findViewById(R.id.linear_layout_oferta_list_item);
            holder.categoria = (TextView) convertView.findViewById(R.id.textView_categoria);
            holder.descripcion = (TextView) convertView.findViewById(R.id.textView_decripcion);
            holder.hora = (TextView) convertView.findViewById(R.id.textView_horas);
            holder.precio = (TextView) convertView.findViewById(R.id.textView_precio);
            holder.moneda_bandera = (ImageView) convertView.findViewById(R.id.imageView_moneda_bandera);
            holder.fecha = (TextView) convertView.findViewById(R.id.textView_fecha);
            holder.requiere_ingles = (CheckBox) convertView.findViewById(R.id.checkBox_requiere_ingles);



            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Trabajo trabajo= trabajos.get(i);

        holder.categoria.setText(trabajo.getCategoria().getDescripcion());
        holder.descripcion.setText(trabajo.getDescripcion());
        holder.hora.setText(context.getResources().getString(R.string.horas_trabajo)
                            +" "+trabajo.getHorasPresupuestadas()
                            +" "+context.getResources().getString(R.string.max_trabajo));
        holder.precio.setText(context.getResources().getString(R.string.pesos_trabajo)
                                +" "+f.format(trabajo.getPrecioMaximoHora()));

        //1 US$ 2Euro 3 AR$- 4 Libra 5 R$
        switch (trabajos.get(i).getMonedaPago()) {
            case (1):
                holder.moneda_bandera.setImageDrawable(context.getResources().getDrawable(R.drawable.us));
                break;
            case (2):
                holder.moneda_bandera.setImageDrawable(context.getResources().getDrawable(R.drawable.eu));
                break;
            case (3):
                holder.moneda_bandera.setImageDrawable(context.getResources().getDrawable(R.drawable.ar));
                break;
            case (4):
                holder.moneda_bandera.setImageDrawable(context.getResources().getDrawable(R.drawable.uk));
                break;
            case (5):
                holder.moneda_bandera.setImageDrawable(context.getResources().getDrawable(R.drawable.br));
                break;
        }

        holder.fecha.setText(context.getResources().getString(R.string.fecha_fin_trabajo)
                                +" "+df.format(trabajo.getFechaEntrega()));
        holder.requiere_ingles.setChecked(trabajo.getRequiereIngles());

        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onItemLongClickListener.onItemLongClick(trabajos.get(position));
                return false;
            }
        });

        return convertView;
    }

    // Establece el listener a informar cuando se hace click sobre un ítem.
    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        onItemLongClickListener = listener;
    }

    // Interfaz que debe implementar el listener para cuando se haga click sobre un elemento.
    public interface OnItemLongClickListener {

        void onItemLongClick(Trabajo trabajo);

    }

    static class ViewHolder {
        TextView categoria;
        TextView descripcion;
        TextView hora;
        TextView precio;
        ImageView moneda_bandera;
        TextView fecha;
        CheckBox requiere_ingles;
        LinearLayout linearLayout;
    }

}
