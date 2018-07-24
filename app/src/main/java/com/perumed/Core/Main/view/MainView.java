package com.perumed.Core.Main.view;

import com.perumed.Networking.models.response.MedicamentoModel;

import java.util.List;

public interface MainView {

    void onSuccessListMedicamentosByNombre(List<MedicamentoModel> listMedicamentos);

    void goToPriceListActivity(MedicamentoModel item);

    void onError(String msg);

}
