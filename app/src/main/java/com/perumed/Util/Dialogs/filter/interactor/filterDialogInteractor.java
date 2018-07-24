package com.perumed.Util.Dialogs.filter.interactor;

import com.perumed.Networking.RetrofitClient;
import com.perumed.Networking.models.request.ProvinciasBody;
import com.perumed.Networking.models.response.ResultProvinciasModel;
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

    public void getListProvinciasByDepartamento(ProvinciasBody body){
        networking.getProvincias(body).enqueue(new Callback<ResultProvinciasModel>() {
            @Override
            public void onResponse(Call<ResultProvinciasModel> call, Response<ResultProvinciasModel> response) {
                if(response.isSuccessful()) {
                    iFilterDialogPresenter.onSuccessListProvinciasByDepartamento(response.body().getProvincias());
                }
            }

            @Override
            public void onFailure(Call<ResultProvinciasModel> call, Throwable t) {
                iFilterDialogPresenter.onError("Unable to submit post to API.");
            }
        });
    }

}
