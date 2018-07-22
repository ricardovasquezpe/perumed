package com.perumed.Networking.models.response;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class ResultMedicamentosModel {
    @SerializedName("d")
    private List<MedicamentoModel> medicamentos = new ArrayList<MedicamentoModel>();


    public List<MedicamentoModel> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<MedicamentoModel> medicamentos) {
        this.medicamentos = medicamentos;
    }
}
