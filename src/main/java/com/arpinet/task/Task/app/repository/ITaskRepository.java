package com.arpinet.task.Task.app.repository;
import com.arpinet.task.Task.app.models.Task;
import com.arpinet.task.Task.app.requests.TaskRequest;
import com.arpinet.task.Task.app.responses.ResponseBDList;

import java.util.Map;

public interface ITaskRepository {
    public ResponseBDList<Task> listarTask();
    public String guardarTask(TaskRequest data);
    public String eliminarTask(Integer codigoTarea);
    public String actualizarTask(Integer codigoTarea, Map<String,Object> data);
}
