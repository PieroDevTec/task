package com.arpinet.task.Task.app.services.impl;

import com.arpinet.task.Task.app.models.Task;
import com.arpinet.task.Task.app.repository.ITaskRepository;
import com.arpinet.task.Task.app.requests.TaskRequest;
import com.arpinet.task.Task.app.responses.ResponseBDList;
import com.arpinet.task.Task.app.responses.ResponseGeneral;
import com.arpinet.task.Task.app.responses.ResponseGeneralList;
import com.arpinet.task.Task.app.services.ITaskService;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TaskService implements ITaskService {
    private static final Logger log = LoggerFactory.getLogger(TaskService.class);
    @Autowired
    private ITaskRepository repo;

    @Override
    public ResponseGeneralList<Task> listTask() {
        ResponseGeneralList<Task> resp = new ResponseGeneralList<>();
        ResponseBDList<Task> respBD = new ResponseBDList<>();
        respBD = repo.listarTask();
        if(respBD.getCode().equals("0")){
            log.info("Mensaje BD => "+ respBD.getCode()+"::"+respBD.getMsj());
            resp.setIcon("success");
            resp.setMensajeApi("Se encontraro resultados satisfactoriamente.");
            resp.setLista(respBD.getLista());
            return resp;
        }else if(respBD.getCode().equals("1")){
            log.error("Mensaje BD => "+ respBD.getCode()+"::"+respBD.getMsj());
            resp.setMensajeApi("Ha ocurrido un problema, intente nuevamente por favor.");
            resp.setLista(List.of());
            return resp;
        }else{
            resp.setMensajeApi("Proceso Inesperado.");
            return resp;
        }
    }

    @Override
    public ResponseGeneral guardarTask(TaskRequest data) {
        ResponseGeneral resp = new ResponseGeneral();
        if(data.getTaskNombre() == null || data.getTaskDescripcion() == null){
            resp.setIcon("warning");
            resp.setMensajeApi("Ingrese el nombre y la descricion de la tarea.");
            return resp;
        }
        String codigo = repo.guardarTask(data);
        if(codigo.equals("2")){
            resp.setIcon("warning");
            resp.setMensajeApi("No se ha podido guardar la tarea, intente nuevamente.");
            return resp;
        }
        resp.setIcon("success");
        resp.setMensajeApi("Tarea guardada satisfactoriamente.");
        return resp;
    }

    @Override
    public ResponseGeneral eliminarTarea(Integer codigoTarea) {
        ResponseGeneral resp = new ResponseGeneral();
        if(codigoTarea == null){
            log.warn("No se ha obtenido el codigo de la tarea.");
            resp.setIcon("warning");
            resp.setMensajeApi("Ocurrio un error el proceso.");
            return resp;
        }
        String codigo = repo.eliminarTask(codigoTarea);
        if(codigo.equalsIgnoreCase("1")){
            resp.setIcon("warning");
            resp.setMensajeApi("Codigo no Identificado.");
            return  resp;
        }else if(codigo.equalsIgnoreCase("2")){
            resp.setIcon("error");
            resp.setMensajeApi("Error Internal Proceso.");
            return resp;
        }
        resp.setIcon("success");
        resp.setMensajeApi("Se elimino la tarea correctamente.");
        return resp;
    }

    @Override
    public ResponseGeneral actualizarTarea(Integer codigoTarea, Map<String, Object> data) {
        ResponseGeneral resp =  new ResponseGeneral();
        String codigo = repo.actualizarTask(codigoTarea,data);
        if(codigo.equals("0")){
            resp.setIcon("warning");
            resp.setMensajeApi("No se realizo la actualizacion de la tarea.");
            return resp;
        }
        resp.setIcon("success");
        resp.setMensajeApi("Se actualizado satisfactoriamente la tarea.");
        return resp;
    }
}
