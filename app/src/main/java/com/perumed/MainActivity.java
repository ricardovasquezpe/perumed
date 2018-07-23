package com.perumed;

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

import com.perumed.Networking.RetrofitClient;
import com.perumed.Networking.models.request.MedicamentosBody;
import com.perumed.Networking.models.response.MedicamentoModel;
import com.perumed.Networking.models.response.ResultMedicamentosModel;
import com.perumed.Networking.services.MedicamentosService;
import com.perumed.Util.Adapters.medicamentosListAdapter;
import com.perumed.Util.Dialogs.loadingDialog;
import com.perumed.Util.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    MedicamentosService medicamentosService;
    RecyclerView rv_medicamentos;
    medicamentosListAdapter da_medicamentos;
    List<MedicamentoModel> medicamentosList;
    EditText et_search_med;

    loadingDialog loading;

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

        medicamentosService = RetrofitClient.getClient(Util.BASE_URL).create(MedicamentosService.class);

    }

    public void initUIViews(){
        rv_medicamentos = findViewById(R.id.rv_medicamentos_act_main);
        et_search_med   = findViewById(R.id.et_search_med_act_main);
    }

    public void initVariables(){
        medicamentosList = new ArrayList<>();
        loading          = new loadingDialog(this);
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
    }

    public void hideKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(getApplication().INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void getListMedicamentos(){
        loading.show();
        MedicamentosBody med = new MedicamentosBody();
        med.setAvanzado("");
        med.setTerm(et_search_med.getText().toString().toLowerCase());

        medicamentosService.getMedicamentos(med).enqueue(new Callback<ResultMedicamentosModel>() {
            @Override
            public void onResponse(Call<ResultMedicamentosModel> call, Response<ResultMedicamentosModel> response) {
                if(response.isSuccessful()) {
                    medicamentosList.clear();
                    medicamentosList.addAll(response.body().getMedicamentos());
                    da_medicamentos.notifyDataSetChanged();
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResultMedicamentosModel> call, Throwable t) {
                Log.e("RESPONSE", "Unable to submit post to API.");
            }
        });
    }

}
