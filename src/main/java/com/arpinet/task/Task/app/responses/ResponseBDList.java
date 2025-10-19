package com.arpinet.task.Task.app.responses;

import java.util.List;

public class ResponseBDList<T> extends  ResponseBD{
    private List<T> lista;

    public List<T> getLista() {
        return lista;
    }

    public void setLista(List<T> lista) {
        this.lista = lista;
    }
}
