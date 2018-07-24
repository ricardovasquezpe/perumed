package com.perumed.Core.Main.view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.perumed.Core.Main.presenter.MainPresenter;
import com.perumed.Core.Main.presenter.iMainPresenter;
import com.perumed.Networking.RetrofitClient;
import com.perumed.Networking.models.request.MedicamentosBody;
import com.perumed.Networking.models.response.MedicamentoModel;
import com.perumed.Networking.models.response.ResultMedicamentosModel;
import com.perumed.Networking.services.MedicamentosService;
import com.perumed.R;
import com.perumed.Util.Adapters.medicamentosListAdapter;
import com.perumed.Util.Dialogs.loadingDialog;
import com.perumed.Util.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainView{

    MedicamentosService medicamentosService;
    RecyclerView rv_medicamentos;
    medicamentosListAdapter da_medicamentos;
    List<MedicamentoModel> medicamentosList;
    EditText et_search_med;
    TextView tv_limpiar;
    RelativeLayout rl_titulo;
    View v_sep_titulo;
    ScrollView sv_medicamentos;
    RelativeLayout rv_central_image;

    loadingDialog loading;

    iMainPresenter iMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        initVariables();
        initUIViews();
        initActions();

        rv_medicamentos.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        da_medicamentos = new medicamentosListAdapter(medicamentosList);
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
    }

    public void initVariables(){
        medicamentosList = new ArrayList<>();
        loading          = new loadingDialog(this);
        iMainPresenter   = new MainPresenter(this);
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
    public void onError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT);
    }
}