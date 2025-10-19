package com.arpinet.task.Task.app.requests;

public class TaskRequest {
    private String taskNombre;
    private String taskDescripcion;

    public String getTaskNombre() {
        return taskNombre;
    }

    public void setTaskNombre(String taskNombre) {
        this.taskNombre = taskNombre;
    }

    public String getTaskDescripcion() {
        return taskDescripcion;
    }

    public void setTaskDescripcion(String taskDescripcion) {
        this.taskDescripcion = taskDescripcion;
    }

    @Override
    public String toString() {
        return "TaskRequest{" +
                "taskNombre='" + taskNombre + '\'' +
                ", taskDescripcion='" + taskDescripcion + '\'' +
                '}';
    }
}
