package com.perumed.Util.Dialogs.filter.presenter;

import com.perumed.Networking.models.request.ProvinciasBody;
import com.perumed.Networking.models.response.ProvinciaModel;
import com.perumed.Util.Dialogs.filter.interactor.filterDialogInteractor;
import com.perumed.Util.Dialogs.filter.view.filterDialog;
import com.perumed.Util.Dialogs.filter.view.filterDialogView;

import java.util.List;

public class filterDialogPresenter implements iFilterDialogPresenter{
    filterDialogView filterDialogView;
    filterDialogInteractor filterDialogInteractor = new filterDialogInteractor(this);

    public filterDialogPresenter(filterDialog dialog){
        filterDialogView = dialog;
    }

    @Override
    public void getListProvinciasByDepartamento(String cod) {
        ProvinciasBody body = new ProvinciasBody();
        body.setCod_dep(cod);
        filterDialogInteractor.getListProvinciasByDepartamento(body);
    }

    @Override
    public void onSuccessListProvinciasByDepartamento(List<ProvinciaModel> provincias) {
        filterDialogView.onSuccessListProvinciasByDepartamento(provincias);
    }

    @Override
    public void onError(String msg) {
        filterDialogView.onError(msg);
    }
}
