package com.perumed.Util.Dialogs.filter.view;

import com.perumed.Networking.models.response.UbigeoModel;

import java.util.List;

public interface filterDialogView {

    void onSuccessListUbigeoBySearch(List<UbigeoModel> provincias, String type);

    void onError(String msg);

}
