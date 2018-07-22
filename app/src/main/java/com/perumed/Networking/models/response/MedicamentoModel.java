package com.perumed.Networking.models.response;

import com.google.gson.annotations.SerializedName;

public class MedicamentoModel {
    @SerializedName("id")
    private String id;
    @SerializedName("__type")
    private String type;
    @SerializedName("value")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
