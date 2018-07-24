package com.perumed.Util.Dialogs.filter.interactor;

import com.perumed.Networking.RetrofitClient;
import com.perumed.Networking.models.request.DistritoBody;
import com.perumed.Networking.models.request.ProvinciaBody;
import com.perumed.Networking.models.response.ResultUbigeoModel;
import com.perumed.Networking.services.Networking;
import com.perumed.Util.Dialogs.filter.presenter.iFilterDialogPresenter;
import com.perumed.Util.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class filterDialogInteractor {
    iFilterDialogPresenter iFilterDialogPresenter;
    Networking networking = RetrofitClient.getClient(Util.BASE_URL).create(Networking.class);

    public filterDialogInteractor(iFilterDialogPresenter presenter){
        iFilterDialogPresenter = presenter;
    }

    public void getListProvinciasBySearch(ProvinciaBody body){
        networking.getProvincias(body).enqueue(new Callback<ResultUbigeoModel>() {
            @Override
            public void onResponse(Call<ResultUbigeoModel> call, Response<ResultUbigeoModel> response) {
                if(response.isSuccessful()) {
                    iFilterDialogPresenter.onSuccessListUbigeoBySearch(response.body().getResult(), "prov");
                }
            }

            @Override
            public void onFailure(Call<ResultUbigeoModel> call, Throwable t) {
                iFilterDialogPresenter.onError("Unable to submit post to API.");
            }
        });
    }

    public void getListDistritosBySearch(DistritoBody body){
        networking.getDistritos(body).enqueue(new Callback<ResultUbigeoModel>() {
            @Override
            public void onResponse(Call<ResultUbigeoModel> call, Response<ResultUbigeoModel> response) {
                if(response.isSuccessful()) {
                    iFilterDialogPresenter.onSuccessListUbigeoBySearch(response.body().getResult(), "dist");
                }
            }

            @Override
            public void onFailure(Call<ResultUbigeoModel> call, Throwable t) {
                iFilterDialogPresenter.onError("Unable to submit post to API.");
            }
        });
    }

}
