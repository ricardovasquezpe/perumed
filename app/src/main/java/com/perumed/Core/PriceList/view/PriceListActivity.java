package com.perumed.Core.PriceList.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.perumed.Core.PriceList.presenter.PriceListPresenter;
import com.perumed.Core.PriceList.presenter.iPriceListPresenter;
import com.perumed.R;

import java.util.List;

public class PriceListActivity extends AppCompatActivity implements PriceListView{

    iPriceListPresenter presenter;
    String grupoExtra;
    String conExtra;
    String ffsExtra;
    String ubigeoExtra;
    String cadExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_list);
        getSupportActionBar().hide();

        initVariables();
        initUIViews();
        initActions();

        grupoExtra  = getIntent().getStringExtra("EXTRA_GROUP_MED");
        conExtra    = getIntent().getStringExtra("EXTRA_CON_MED");
        ffsExtra    = getIntent().getStringExtra("EXTRA_FFS_MED");
        ubigeoExtra = getIntent().getStringExtra("EXTRA_UBIGEO_MED");
        cadExtra    = getIntent().getStringExtra("EXTRA_CAD_MED");

        if(!grupoExtra.isEmpty() && !conExtra.isEmpty() && !ffsExtra.isEmpty() && !ubigeoExtra.isEmpty() && !cadExtra.isEmpty()){
            initData();
        }
    }

    public void initVariables(){
        presenter = new PriceListPresenter(this);
    }

    public void initUIViews(){

    }

    public void initActions(){

    }

    public void initData(){
        presenter.getListPriceByMedicamento(grupoExtra, conExtra, ffsExtra, ubigeoExtra, cadExtra);
    }

    @Override
    public void onSuccessListPriceByMedicamento(List<Object> precios) {

    }

    @Override
    public void onError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT);
    }
}
