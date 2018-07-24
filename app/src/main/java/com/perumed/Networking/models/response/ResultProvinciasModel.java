package com.perumed.Networking.models.response;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class ResultProvinciasModel {
    @SerializedName("d")
    private List<ProvinciaModel> provincias = new ArrayList<ProvinciaModel>();

    public List<ProvinciaModel> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<ProvinciaModel> provincias) {
        this.provincias = provincias;
    }
}
