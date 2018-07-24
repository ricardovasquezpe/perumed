package com.perumed.Core.PriceList.presenter;

import java.util.List;

public interface iPriceListPresenter {

    void getListPriceByMedicamento(String grupo, String con, String ffs, String ubigeo, String cad);

    void onSuccessListPriceByMedicamento(List<Object> precios);

    void onError(String msg);

}
