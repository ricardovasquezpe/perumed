package com.perumed.Networking.models.response;

import com.google.gson.annotations.SerializedName;

public class UbigeoModel {
    @SerializedName("__type")
    private
    String __type;
    @SerializedName("Codigo")
    private
    String Codigo;
    @SerializedName("Descripcion")
    private
    String Descripcion;


    public String get__type() {
        return __type;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
