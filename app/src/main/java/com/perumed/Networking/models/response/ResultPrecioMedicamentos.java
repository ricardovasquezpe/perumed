package com.perumed.Networking.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResultPrecioMedicamentos {
    @SerializedName("iTotalRecords")
    private
    String iTotalRecords;
    @SerializedName("iTotalDisplayRecords")
    private
    String iTotalDisplayRecords;
    @SerializedName("aaData")
    private
    List<Object> aaData = new ArrayList<>();

    public String getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(String iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public String getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(String iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public List<Object> getAaData() {
        return aaData;
    }

    public void setAaData(List<Object> aaData) {
        this.aaData = aaData;
    }
}
