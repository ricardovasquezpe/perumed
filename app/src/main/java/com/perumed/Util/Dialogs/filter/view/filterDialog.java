package com.perumed.Util.Dialogs.filter.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.perumed.Networking.models.response.ProvinciaModel;
import com.perumed.R;
import com.perumed.Util.Dialogs.filter.presenter.filterDialogPresenter;
import com.perumed.Util.Dialogs.filter.presenter.iFilterDialogPresenter;
import com.perumed.Util.Dialogs.loadingDialog;
import com.perumed.Util.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class filterDialog extends Dialog implements filterDialogView{

    Spinner s_departamento;
    HashMap<String, String> listHashDepartamentos = new HashMap<String, String>();
    List<String> listDepartamentos = new ArrayList<>();
    iFilterDialogPresenter iFilterDialogPresenter;
    loadingDialog loading;

    public filterDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.filter_dialog);
        initUIView();
        initVariable();
        initData();
        initActions();
    }

    public void initUIView(){
        s_departamento = findViewById(R.id.s_departamento_fil_dialog);
    }

    public void initVariable(){
        iFilterDialogPresenter = new filterDialogPresenter(this);
        loading                = new loadingDialog(this.getContext());
    }

    public void initData(){
        listHashDepartamentos = Util.GETDEPARTAMENTOS();
        listDepartamentos     = Util.GETDEPARTAMENTOSSTRINGS();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, listDepartamentos);
        s_departamento.setAdapter(adapter);
    }

    public void initActions(){
        s_departamento.setSelection(0,false);
        s_departamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                loading.show();
                String nombreDep = listDepartamentos.get(position);
                String codDep    = listHashDepartamentos.get(nombreDep);
                if(!codDep.isEmpty()){
                    iFilterDialogPresenter.getListProvinciasByDepartamento(codDep);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
    }

    @Override
    public void onSuccessListProvinciasByDepartamento(List<ProvinciaModel> provincias) {
        loading.dismiss();
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(this.getContext(), msg, Toast.LENGTH_SHORT);
    }
}
