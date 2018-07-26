package com.perumed.Util.Models;

public class PrecioMedicamentoModel {

    private String id;
    private String tipo_farmacia;
    private String fecha;
    private String nombre;
    private String laboratario;
    private String numero;
    private String nombre_farmacia;
    private String precio;
    private String link;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo_farmacia() {
        return tipo_farmacia;
    }

    public void setTipo_farmacia(String tipo_farmacia) {
        this.tipo_farmacia = tipo_farmacia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLaboratario() {
        return laboratario;
    }

    public void setLaboratario(String laboratario) {
        this.laboratario = laboratario;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombre_farmacia() {
        return nombre_farmacia;
    }

    public void setNombre_farmacia(String nombre_farmacia) {
        this.nombre_farmacia = nombre_farmacia;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
