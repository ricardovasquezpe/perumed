package com.perumed.Util.Dialogs.filter.presenter;

import com.perumed.Networking.models.response.ProvinciaModel;

import java.util.List;

public interface iFilterDialogPresenter {
    //REQUEST
    void getListProvinciasByDepartamento(String cod);

    //RESPOSE
    void onSuccessListProvinciasByDepartamento(List<ProvinciaModel> provincias);
    void onError(String msg);

}
