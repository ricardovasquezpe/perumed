package com.perumed.Util.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.perumed.Core.Main.view.MainActivity;
import com.perumed.Core.Main.view.MainView;
import com.perumed.Core.PriceList.view.PriceListActivity;
import com.perumed.Core.PriceList.view.PriceListView;
import com.perumed.Networking.models.response.MedicamentoModel;
import com.perumed.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class precioMedicamentosListAdapter extends RecyclerView.Adapter<precioMedicamentosListAdapter.ViewHolder>{
    List<Object> data;
    Context context;
    PriceListView view;

    public precioMedicamentosListAdapter(List<Object> data, PriceListActivity activity) {
        this.data = data;
        this.view = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        this.context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.precio_medicamento_row, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Object item = data.get(position);

        try{
            String array = item.toString();
            JSONObject arrayObj = new JSONObject(array);
        }catch(Exception e){
            String g = "";
        }

        //holder.name.setText(item.getName());
        if(position == data.size() - 1){
            holder.separator.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView price;
        public ImageView map;
        public View separator;

        public ViewHolder(View v) {
            super(v);
            name      = (TextView) v.findViewById(R.id.nombre_price_med_row);
            price     = (TextView) v.findViewById(R.id.precio_price_med_row);
            map       = (ImageView) v.findViewById(R.id.map_price_med_row);
            separator = (View) v.findViewById(R.id.separator_price_med_row);

            map.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Object item = data.get(getAdapterPosition());
                    //view.goToPriceListActivity(item);
                }
            });
        }
    }
}
