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
import com.perumed.Networking.models.response.UbigeoModel;
import com.perumed.R;
import com.perumed.Util.Dialogs.filter.presenter.filterDialogPresenter;
import com.perumed.Util.Dialogs.filter.presenter.iFilterDialogPresenter;
import com.perumed.Util.Dialogs.loadingDialog;
import com.perumed.Util.Util;
import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class filterDialog extends Dialog implements filterDialogView{

    Spinner s_departamento;
    Spinner s_provincia;
    Spinner s_distrito;

    HashMap<String, String> listHashDepartamentos;
    List<String> listDepartamentos;
    HashMap<String, String> listHashProvincias;
    List<String> listProvincias;
    HashMap<String, String> listHashDistritos;
    List<String> listDistritos;

    iFilterDialogPresenter iFilterDialogPresenter;
    loadingDialog loading;

    String codDepTemp  = "";
    String codProvTemp = "";
    String codDistTemp = "";

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
        s_provincia    = findViewById(R.id.s_provincia_fil_dialog);
        s_distrito     = findViewById(R.id.s_distrito_fil_dialog);
    }

    public void initVariable(){
        iFilterDialogPresenter = new filterDialogPresenter(this);
        loading                = new loadingDialog(this.getContext());
        listHashDepartamentos  = new HashMap<String, String>();
        listDepartamentos      = new ArrayList<>();
        listHashProvincias     = new HashMap<String, String>();
        listProvincias         = new ArrayList<>();
        listHashDistritos      = new HashMap<String, String>();
        listDistritos          = new ArrayList<>();
    }

    public void initData(){
        listHashDepartamentos = Util.GETDEPARTAMENTOS();
        listDepartamentos     = Util.GETDEPARTAMENTOSSTRINGS();
        ArrayAdapter<String> adapter_dep = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, listDepartamentos);
        s_departamento.setAdapter(adapter_dep);

        listProvincias.add("Seleccione una Provincia");
        ArrayAdapter<String> adapter_prov = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, listProvincias);
        s_provincia.setAdapter(adapter_prov);


        listDistritos.add("Seleccione un Distrito");
        ArrayAdapter<String> adapter_dist = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, listDistritos);
        s_distrito.setAdapter(adapter_dist);

    }

    public void initActions(){
        s_departamento.setSelection(0,false);
        s_departamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String nombreDep = listDepartamentos.get(position);
                String codDep    = listHashDepartamentos.get(nombreDep);
                if(codDep != null && !codDep.isEmpty()){
                    loading.show();
                    codDepTemp = codDep;
                    searchUbigeo();
                }else{
                    codDepTemp = "";
                    limpiarProvinciasSpinner();
                    limpiarDistritosSpinner();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

        s_provincia.setSelection(0, false);
        s_provincia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String nombreProv = listProvincias.get(position);
                String codProv    = listHashProvincias.get(nombreProv);
                if(codProv != null && !codProv.isEmpty()){
                    loading.show();
                    codProvTemp = codProv;
                    searchUbigeo();
                }else{
                    limpiarDistritosSpinner();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

        s_distrito.setSelection(0, false);
        s_distrito.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String nombreDist = listDistritos.get(position);
                String codDist    = listHashDistritos.get(nombreDist);
                if(codDist == null || codDist.isEmpty()){
                    codDistTemp = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
    }

    public void limpiarProvinciasSpinner(){
        listProvincias.clear();
        listHashProvincias.clear();
        listProvincias.add("Seleccione una Provincia");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, listProvincias);
        s_provincia.setAdapter(adapter);
        s_provincia.setSelection(0, false);
        this.codProvTemp = "";
    }

    public void limpiarDistritosSpinner(){
        listDistritos.clear();
        listHashDistritos.clear();
        listDistritos.add("Seleccione un Distrito");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, listDistritos);
        s_distrito.setAdapter(adapter);
        s_distrito.setSelection(0, false);
        this.codDistTemp = "";
    }

    public void searchUbigeo(){
        iFilterDialogPresenter.getListUbigeoBySearch(this.codDepTemp, this.codProvTemp);
    }

    @Override
    public void onSuccessListUbigeoBySearch(List<UbigeoModel> ubigeos, String type) {
        if(type.equals("prov")){
            listProvincias.clear();
            listHashProvincias.clear();
            listProvincias.add("Seleccione una Provincia");

            for (UbigeoModel prov : ubigeos){
                listProvincias.add(StringUtils.capitalize(prov.getDescripcion().toLowerCase()));
                listHashProvincias.put(StringUtils.capitalize(prov.getDescripcion().toLowerCase()), prov.getCodigo());
            }

            if(listProvincias.size() > 0){
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, listProvincias);
                s_provincia.setAdapter(adapter);
            }
            s_provincia.setSelection(0, false);


        }else if(type.equals("dist")){
            listDistritos.clear();
            listHashDistritos.clear();
            listDistritos.add("Seleccione un Distrito");

            for (UbigeoModel dist : ubigeos){
                listDistritos.add(StringUtils.capitalize(dist.getDescripcion().toLowerCase()));
                listHashDistritos.put(StringUtils.capitalize(dist.getDescripcion().toLowerCase()), dist.getCodigo());
            }

            if(listDistritos.size() > 0){
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, listDistritos);
                s_distrito.setAdapter(adapter);
            }
            s_distrito.setSelection(0, false);
        }
        loading.dismiss();
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(this.getContext(), msg, Toast.LENGTH_SHORT);
    }
}
