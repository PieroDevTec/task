package com.arpinet.task.Task.app.controllers;

import com.arpinet.task.Task.app.models.Task;
import com.arpinet.task.Task.app.repository.ITaskRepository;
import com.arpinet.task.Task.app.requests.TaskRequest;
import com.arpinet.task.Task.app.responses.ResponseGeneral;
import com.arpinet.task.Task.app.responses.ResponseGeneralList;
import com.arpinet.task.Task.app.services.ITaskService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("v1/task")
@CrossOrigin("*")
public class TaskController {
    @Autowired
    private ITaskService service;


    @RequestMapping(value = "/listar",method = RequestMethod.GET)
    public ResponseEntity<ResponseGeneralList<Task>> listaTask(){
        ResponseGeneralList<Task> response = new ResponseGeneralList<>();
        response = service.listTask();
        if(response.getIcon().equalsIgnoreCase("success")){
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @RequestMapping(value = "/guardar",method = RequestMethod.POST)
    public ResponseEntity<ResponseGeneral> guardarTask(@RequestBody TaskRequest data){
        ResponseGeneral resp = new ResponseGeneral();
        resp = service.guardarTask(data);
        if(resp.getIcon().equalsIgnoreCase("success")){
            return ResponseEntity.status(HttpStatus.CREATED).body(resp);
        }else if(resp.getIcon().equalsIgnoreCase("warning")){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
        }
    }
    @RequestMapping(value = "/eliminar",method = RequestMethod.DELETE)
    public ResponseEntity<ResponseGeneral> eliminarTask(@RequestParam Integer cod){
        ResponseGeneral resp = service.eliminarTarea(cod);
        if(resp.getIcon().equalsIgnoreCase("success")){
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }else if(resp.getIcon().equalsIgnoreCase("warning")){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(resp);
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
        }
    }

    @RequestMapping(value = "/actualizar",method = RequestMethod.PATCH)
    public ResponseEntity<ResponseGeneral> actualizarTask(@RequestParam Integer cod, @RequestBody Map<String,Object> data){
        ResponseGeneral resp = service.actualizarTarea(cod,data);
        if(resp.getIcon().equalsIgnoreCase("warning")){
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }
        if(resp.getIcon().equalsIgnoreCase("success")){
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
    }
}
