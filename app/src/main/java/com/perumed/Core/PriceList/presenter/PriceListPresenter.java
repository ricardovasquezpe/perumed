package com.perumed.Core.PriceList.presenter;

import com.perumed.Core.PriceList.interactor.PriceListInteractor;
import com.perumed.Core.PriceList.view.PriceListActivity;
import com.perumed.Core.PriceList.view.PriceListView;

import java.util.List;

public class PriceListPresenter implements iPriceListPresenter {

    PriceListView view;
    PriceListInteractor interactor;

    public PriceListPresenter(PriceListActivity activity){
        this.view       = activity;
        this.interactor = new PriceListInteractor(this);
    }


    @Override
    public void getListPriceByMedicamento(String grupo, String con, String ffs, String ubigeo, String cad) {
        interactor.getListPriceByMedicamento("", "", "", "", "");
    }

    @Override
    public void onSuccessListPriceByMedicamento(List<Object> precios) {
        view.onSuccessListPriceByMedicamento(precios);
    }

    @Override
    public void onError(String msg) {
        view.onError(msg);
    }
}
