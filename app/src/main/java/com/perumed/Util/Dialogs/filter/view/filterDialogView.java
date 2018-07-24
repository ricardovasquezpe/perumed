package com.perumed.Util.Dialogs.filter.view;

import com.perumed.Networking.models.response.ProvinciaModel;

import java.util.List;

public interface filterDialogView {

    void onSuccessListProvinciasByDepartamento(List<ProvinciaModel> provincias);

    void onError(String msg);

}
