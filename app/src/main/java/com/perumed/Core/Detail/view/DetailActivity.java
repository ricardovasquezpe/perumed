package com.perumed.Core.Detail.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.perumed.R;

public class DetailActivity extends AppCompatActivity implements DetailView {

    WebView wv_detalle;
    String idp;
    String ide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();

        idp = getIntent().getStringExtra("EXTRA_IDP");
        ide = getIntent().getStringExtra("EXTRA_IDE");

        initVariables();
        initUIViews();
        initActions();
    }

    public void initUIViews(){
        wv_detalle = findViewById(R.id.wv_detalle_act_detail);
    }

    public void initVariables(){
    }

    public void initActions(){
    }

    public void initWebView(){
        wv_detalle.getSettings().setJavaScriptEnabled(true);
        wv_detalle.setWebChromeClient(new WebChromeClient());
        wv_detalle.loadUrl("http://observatorio.digemid.minsa.gob.pe/Precios/ProcesoL/Consulta/FichaProducto.aspx?idp=" + idp + "&ide=" + ide);
    }

}
