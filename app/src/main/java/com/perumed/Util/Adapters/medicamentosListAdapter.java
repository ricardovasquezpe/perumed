package com.perumed.Util.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.perumed.Core.Main.view.MainActivity;
import com.perumed.Core.Main.view.MainView;
import com.perumed.Networking.models.response.MedicamentoModel;
import com.perumed.R;
import java.util.List;

public class medicamentosListAdapter extends RecyclerView.Adapter<medicamentosListAdapter.ViewHolder>{
    List<MedicamentoModel> data;
    Context context;
    MainView view;

    public medicamentosListAdapter(List<MedicamentoModel> data, MainActivity activity) {
        this.data = data;
        this.view = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        this.context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.medicamento_row, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final MedicamentoModel item = data.get(position);
        holder.name.setText(item.getName());
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
        public View separator;

        public ViewHolder(View v) {
            super(v);
            name      = (TextView) v.findViewById(R.id.nombre_med_row);
            separator = (View) v.findViewById(R.id.separator_med_row);



            v.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    MedicamentoModel item = data.get(getAdapterPosition());
                    view.goToPriceListActivity(item);
                }
            });
        }
    }
}
