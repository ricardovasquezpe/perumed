package com.perumed.Core.PriceList.view;

import java.util.List;

public interface PriceListView {

    void onError(String msg);

    void onSuccessListPriceByMedicamento(List<Object> precios);

}
