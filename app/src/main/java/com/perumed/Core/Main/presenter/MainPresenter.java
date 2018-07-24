package com.perumed.Core.Main.presenter;

import com.perumed.Core.Main.interactor.MainInteractor;
import com.perumed.Core.Main.view.MainActivity;
import com.perumed.Core.Main.view.MainView;
import com.perumed.Networking.models.request.MedicamentosBody;
import com.perumed.Networking.models.response.MedicamentoModel;

import java.util.List;

public class MainPresenter implements iMainPresenter{

    MainView mainView;
    MainInteractor mainInteractor = new MainInteractor(this);

    public MainPresenter(MainActivity activity){
        this.mainView = activity;
    }

    public void getListMedicamentosByNombre(String nombre){
        MedicamentosBody med = new MedicamentosBody();
        med.setAvanzado("");
        med.setTerm(nombre);
        mainInteractor.getListMedicamentosByNombre(med);
    }

    @Override
    public void onSuccessListMedicamentosByNombre(List<MedicamentoModel> medicamentos) {
        mainView.onSuccessListMedicamentosByNombre(medicamentos);
    }

    @Override
    public void onError(String msg) {
        mainView.onError(msg);
    }

}
