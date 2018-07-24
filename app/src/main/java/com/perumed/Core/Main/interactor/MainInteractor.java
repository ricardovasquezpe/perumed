package com.perumed.Core.Main.interactor;

import com.perumed.Core.Main.presenter.MainPresenter;
import com.perumed.Core.Main.presenter.iMainPresenter;
import com.perumed.Networking.RetrofitClient;
import com.perumed.Networking.models.request.MedicamentosBody;
import com.perumed.Networking.models.response.ResultMedicamentosModel;
import com.perumed.Networking.services.Networking;
import com.perumed.Util.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainInteractor {

    iMainPresenter iMainPresenter;
    Networking networking = RetrofitClient.getClient(Util.BASE_URL).create(Networking.class);

    public MainInteractor(MainPresenter presenter){
        this.iMainPresenter = presenter;
    }

    public void getListMedicamentosByNombre(MedicamentosBody body){
        networking.getMedicamentos(body).enqueue(new Callback<ResultMedicamentosModel>() {
            @Override
            public void onResponse(Call<ResultMedicamentosModel> call, Response<ResultMedicamentosModel> response) {
                if(response.isSuccessful()) {
                    iMainPresenter.onSuccessListMedicamentosByNombre(response.body().getMedicamentos());
                }
            }

            @Override
            public void onFailure(Call<ResultMedicamentosModel> call, Throwable t) {
                iMainPresenter.onError("Unable to submit post to API.");
            }
        });
    }

}
