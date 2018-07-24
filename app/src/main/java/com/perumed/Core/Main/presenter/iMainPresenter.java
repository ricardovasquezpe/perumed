package com.perumed.Core.Main.presenter;

import com.perumed.Networking.models.response.MedicamentoModel;

import java.util.List;

public interface iMainPresenter {

    //REQUEST
    void getListMedicamentosByNombre(String nombre);

    //RESPOSE
    void onSuccessListMedicamentosByNombre(List<MedicamentoModel> medicamentos);
    void onError(String msg);

}
