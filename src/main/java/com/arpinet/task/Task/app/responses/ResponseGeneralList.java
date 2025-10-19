package com.arpinet.task.Task.app.responses;

import java.util.List;

public class ResponseGeneralList<T> extends  ResponseGeneral {
    private List<T> lista;

    public List<T> getLista() {
        return lista;
    }

    public void setLista(List<T> lista) {
        this.lista = lista;
    }
}
