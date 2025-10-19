package com.arpinet.task.Task.app.responses;

public class ResponseGeneral {
    private String nombreSistema = "Aplicacion de Tareas";
    private String icon = "error";
    private String mensajeApi = "Proceso no validado.";

    public String getMensajeApi() {
        return mensajeApi;
    }

    public void setMensajeApi(String mensajeApi) {
        this.mensajeApi = mensajeApi;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNombreSistema() {
        return nombreSistema;
    }

    public void setNombreSistema(String nombreSistema) {
        this.nombreSistema = nombreSistema;
    }

    @Override
    public String toString() {
        return "ResponseGeneral{" +
                "nombreSistema='" + nombreSistema + '\'' +
                ", icon='" + icon + '\'' +
                ", mensajeApi='" + mensajeApi + '\'' +
                '}';
    }
}
