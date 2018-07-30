package com.perumed.Core.PriceList.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.perumed.Core.PriceList.presenter.PriceListPresenter;
import com.perumed.Core.PriceList.presenter.iPriceListPresenter;
import com.perumed.R;
import com.perumed.Util.Adapters.medicamentosListAdapter;
import com.perumed.Util.Adapters.precioMedicamentosListAdapter;

import java.util.ArrayList;
import java.util.List;

public class PriceListActivity extends AppCompatActivity implements PriceListView{

    iPriceListPresenter presenter;
    String nameExtra;
    String grupoExtra;
    String conExtra;
    String ffsExtra;
    String ubigeoExtra;
    String cadExtra;

    RecyclerView rv_precios_medicamento;
    precioMedicamentosListAdapter da_precio_medicamento;
    List<Object> precioMedicamentoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_list);
        getSupportActionBar().hide();

        initVariables();
        initUIViews();
        initActions();

        rv_precios_medicamento.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        da_precio_medicamento = new precioMedicamentosListAdapter(precioMedicamentoList, this);
        rv_precios_medicamento.setAdapter(da_precio_medicamento);

        nameExtra   = getIntent().getStringExtra("EXTRA_NAME");
        grupoExtra  = getIntent().getStringExtra("EXTRA_GROUP_MED");
        conExtra    = getIntent().getStringExtra("EXTRA_CON_MED");
        ffsExtra    = getIntent().getStringExtra("EXTRA_FFS_MED");
        ubigeoExtra = getIntent().getStringExtra("EXTRA_UBIGEO_MED");
        cadExtra    = getIntent().getStringExtra("EXTRA_CAD_MED");

        if(!grupoExtra.isEmpty() && !conExtra.isEmpty() && !ffsExtra.isEmpty() && !ubigeoExtra.isEmpty() && !cadExtra.isEmpty() && !nameExtra.isEmpty()){
            initData();
        }
    }

    public void initVariables(){
        presenter             = new PriceListPresenter(this);
        precioMedicamentoList = new ArrayList<>();
    }

    public void initUIViews(){
        rv_precios_medicamento = findViewById(R.id.rv_precio_medicamento_act_price_list);
    }

    public void initActions(){

    }

    public void initData(){
        presenter.getListPriceByMedicamento(grupoExtra, conExtra, ffsExtra, ubigeoExtra, cadExtra);
    }

    @Override
    public void onSuccessListPriceByMedicamento(List<Object> precios) {
        precioMedicamentoList.clear();
        precioMedicamentoList.addAll(precios);
        da_precio_medicamento.notifyDataSetChanged();
    }

    @Override
    public void onMapClicked(String url) {
        Intent intent = new Intent(getBaseContext(), PriceListActivity.class);
        intent.putExtra("EXTRA_URL", url);
        startActivity(intent);
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT);
    }
}
