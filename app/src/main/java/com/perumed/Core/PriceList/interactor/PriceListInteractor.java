package com.perumed.Core.PriceList.interactor;

import com.perumed.Core.PriceList.presenter.PriceListPresenter;
import com.perumed.Core.PriceList.presenter.iPriceListPresenter;
import com.perumed.Networking.RetrofitClient;
import com.perumed.Networking.models.response.ResultPrecioMedicamentos;
import com.perumed.Networking.services.Networking;
import com.perumed.Util.Models.PrecioMedicamentoModel;
import com.perumed.Util.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Response;

public class PriceListInteractor {

    Networking networking = RetrofitClient.getClient(Util.BASE_URL).create(Networking.class);
    iPriceListPresenter iPriceListPresenter;

    public PriceListInteractor(PriceListPresenter presenter){
        this.iPriceListPresenter = presenter;
    }

    public void getListPriceByMedicamento(String grupo, String con, String ffs, String ubigeo, String cad){
        networking.getListPreciosMedicamento(grupo, con, ffs, ubigeo, cad).enqueue(new Callback<ResultPrecioMedicamentos>() {
            @Override
            public void onResponse(Call<ResultPrecioMedicamentos> call, Response<ResultPrecioMedicamentos> response) {
                if(response.isSuccessful()) {
                    iPriceListPresenter.onSuccessListPriceByMedicamento(response.body().getAaData());
                }
            }

            @Override
            public void onFailure(Call<ResultPrecioMedicamentos> call, Throwable t) {
                iPriceListPresenter.onError("Unable to submit post to API.");
            }
        });
    }
}
