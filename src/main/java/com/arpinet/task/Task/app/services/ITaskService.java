package com.arpinet.task.Task.app.services;

import com.arpinet.task.Task.app.models.Task;
import com.arpinet.task.Task.app.requests.TaskRequest;
import com.arpinet.task.Task.app.responses.ResponseGeneral;
import com.arpinet.task.Task.app.responses.ResponseGeneralList;

import java.util.List;
import java.util.Map;

public interface ITaskService {
    public ResponseGeneralList<Task> listTask();
    public ResponseGeneral guardarTask(TaskRequest data);
    public ResponseGeneral eliminarTarea(Integer codigoTarea);
    public ResponseGeneral actualizarTarea(Integer codigoTarea, Map<String,Object> data);
}
