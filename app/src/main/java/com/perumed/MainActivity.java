package com.perumed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.perumed.Networking.RetrofitClient;
import com.perumed.Networking.models.request.MedicamentosBody;
import com.perumed.Networking.models.response.ResultMedicamentosModel;
import com.perumed.Networking.services.MedicamentosService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    MedicamentosService medicamentosService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        medicamentosService = RetrofitClient.getClient("http://observatorio.digemid.minsa.gob.pe/").create(MedicamentosService.class);


        MedicamentosBody med = new MedicamentosBody();
        med.setAvanzado("");
        med.setTerm("para");

        medicamentosService.getMedicamentos(med).enqueue(new Callback<ResultMedicamentosModel>() {
            @Override
            public void onResponse(Call<ResultMedicamentosModel> call, Response<ResultMedicamentosModel> response) {
                if(response.isSuccessful()) {
                    String g = "";
                }
                String h = response.message();
            }

            @Override
            public void onFailure(Call<ResultMedicamentosModel> call, Throwable t) {
                String g = t.getMessage();
                Log.e("RESPONSE", "Unable to submit post to API.");
            }
        });

    }
}
