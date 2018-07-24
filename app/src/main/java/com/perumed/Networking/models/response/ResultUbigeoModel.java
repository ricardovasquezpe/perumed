package com.perumed.Networking.models.response;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class ResultUbigeoModel {
    @SerializedName("d")
    private List<UbigeoModel> result = new ArrayList<UbigeoModel>();

    public List<UbigeoModel> getResult() {
        return result;
    }

    public void setResult(List<UbigeoModel> result) {
        this.result = result;
    }
}
