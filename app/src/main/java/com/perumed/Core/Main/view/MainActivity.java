package com.perumed.Core.Main.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.perumed.Core.Main.presenter.MainPresenter;
import com.perumed.Core.Main.presenter.iMainPresenter;
import com.perumed.Core.PriceList.view.PriceListActivity;
import com.perumed.Networking.models.response.MedicamentoModel;
import com.perumed.R;
import com.perumed.Util.Adapters.medicamentosListAdapter;
import com.perumed.Util.Dialogs.filter.view.filterDialog;
import com.perumed.Util.Dialogs.loadingDialog;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView{

    RecyclerView rv_medicamentos;
    medicamentosListAdapter da_medicamentos;
    List<MedicamentoModel> medicamentosList;
    EditText et_search_med;
    TextView tv_limpiar;
    RelativeLayout rl_titulo;
    View v_sep_titulo;
    ScrollView sv_medicamentos;
    RelativeLayout rv_central_image;
    ImageView iv_filter;

    loadingDialog loading;
    filterDialog filter;

    iMainPresenter iMainPresenter;

    String ubigeo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        initVariables();
        initUIViews();
        initActions();

        rv_medicamentos.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        da_medicamentos = new medicamentosListAdapter(medicamentosList, this);
        rv_medicamentos.setAdapter(da_medicamentos);
    }

    public void initUIViews(){
        rv_medicamentos  = findViewById(R.id.rv_medicamentos_act_main);
        et_search_med    = findViewById(R.id.et_search_med_act_main);
        tv_limpiar       = findViewById(R.id.tv_limpiar_act_main);
        rl_titulo        = findViewById(R.id.rl_titulo_act_main);
        v_sep_titulo     = findViewById(R.id.v_sep_titulo_act_main);
        sv_medicamentos  = findViewById(R.id.sv_medicamentos_act_main);
        rv_central_image = findViewById(R.id.rv_central_image_act_main);
        iv_filter        = findViewById(R.id.iv_filter_act_main);
    }

    public void initVariables(){
        medicamentosList = new ArrayList<>();
        loading          = new loadingDialog(this);
        filter = new filterDialog(this);
        filter.newInstance(this);
        iMainPresenter   = new MainPresenter(this);
        ubigeo = "";
    }

    public void initActions(){
        et_search_med.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    hideKeyboard();
                    getListMedicamentos();
                    return true;
                }
                return false;
            }
        });

        tv_limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_search_med.setText("");
                medicamentosList.clear();
                da_medicamentos.notifyDataSetChanged();
                sv_medicamentos.setVisibility(View.GONE);
                rv_central_image.setVisibility(View.VISIBLE);
                tv_limpiar.setVisibility(View.INVISIBLE);
            }
        });

        iv_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter.show();
            }
        });
    }

    public void hideKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(getApplication().INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void getListMedicamentos(){
        String med_nombre = et_search_med.getText().toString().toLowerCase();
        if(med_nombre.length() != 0){
            loading.show();
            iMainPresenter.getListMedicamentosByNombre(med_nombre);
        }else{
            sv_medicamentos.setVisibility(View.GONE);
            rv_central_image.setVisibility(View.VISIBLE);
            tv_limpiar.setVisibility(View.INVISIBLE);
            medicamentosList.clear();
            da_medicamentos.notifyDataSetChanged();
        }
    }

    @Override
    public void setUbigeoValue(String ubigeoVal){
        this.ubigeo = ubigeoVal;
    }

    @Override
    public void onSuccessListMedicamentosByNombre(List<MedicamentoModel> listMedicamentosRet){
        medicamentosList.clear();
        medicamentosList.addAll(listMedicamentosRet);
        da_medicamentos.notifyDataSetChanged();
        if(listMedicamentosRet.size() > 0){
            sv_medicamentos.setVisibility(View.VISIBLE);
            rv_central_image.setVisibility(View.GONE);
            tv_limpiar.setVisibility(View.VISIBLE);
        }else{
            sv_medicamentos.setVisibility(View.GONE);
            rv_central_image.setVisibility(View.VISIBLE);
            tv_limpiar.setVisibility(View.INVISIBLE);
        }
        loading.dismiss();
    }

    @Override
    public void goToPriceListActivity(MedicamentoModel item){
        String[] id_parts = item.getId().split("@");

        Intent intent = new Intent(this, PriceListActivity.class);
        intent.putExtra("EXTRA_NAME", item.getName());
        intent.putExtra("EXTRA_GROUP_MED", id_parts[0]);
        intent.putExtra("EXTRA_CON_MED", id_parts[2].replace(' ', '*'));
        intent.putExtra("EXTRA_FFS_MED", id_parts[3]);
        if(ubigeo.isEmpty()){
            intent.putExtra("EXTRA_UBIGEO_MED", "15");
        } else {
            intent.putExtra("EXTRA_UBIGEO_MED", ubigeo);
        }
        intent.putExtra("EXTRA_CAD_MED", item.getName().replace(' ', '*'));
        startActivity(intent);
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT);
    }
}