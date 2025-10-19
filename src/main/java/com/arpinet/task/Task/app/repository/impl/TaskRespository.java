package com.arpinet.task.Task.app.repository.impl;

import com.arpinet.task.Task.app.models.Task;
import com.arpinet.task.Task.app.repository.ITaskRepository;
import com.arpinet.task.Task.app.requests.TaskRequest;
import com.arpinet.task.Task.app.responses.ResponseBDList;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class TaskRespository implements ITaskRepository {
    private static final Set<String> ALLLOWED_COLUMNS = Set.of("task_nomb","task_desc");
    private final JdbcTemplate jdbcTemplate;
    public TaskRespository(@Qualifier("prodJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ResponseBDList<Task> listarTask() {
        ResponseBDList<Task> resp = new ResponseBDList<>();
        try{
            String sql = "SELECT * FROM sf_listar_task()";
            List<Task> lista = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Task.class));
            resp.setCode("0");
            resp.setMsj("BD:: Se encontraron resultados satisfactoriamente");
            resp.setLista(lista);
        }catch (Exception e){
            e.printStackTrace();
            resp.setCode("2");
            resp.setMsj(e.getClass()+"::"+e.getMessage());
        }
        return resp;
    }

    @Override
    public String guardarTask(TaskRequest data) {
        String sql = "CALL sp_guar_task(?,?,?)";
        try(Connection conn = jdbcTemplate.getDataSource().getConnection();
            CallableStatement stmt = conn.prepareCall(sql)){
            stmt.setString(1,data.getTaskNombre());
            stmt.setString(2,data.getTaskDescripcion());
            stmt.registerOutParameter(3,Types.VARCHAR);
            stmt.execute();
            return stmt.getString(3);
        } catch (Exception e) {
            e.printStackTrace();
            return "2";
        }
    }

    @Override
    public String eliminarTask(Integer codigoTarea) {
        String sql = "CALL sp_eliminar_task(?,?)";
        try(Connection conn = jdbcTemplate.getDataSource().getConnection();
            CallableStatement stmt = conn.prepareCall(sql)){
            stmt.setInt(1,codigoTarea);
            stmt.registerOutParameter(2,Types.VARCHAR);
            stmt.execute();
            return stmt.getString(2);
        } catch (Exception e) {
            e.printStackTrace();
            return "2";
        }
    }

    @Override
    public String actualizarTask(Integer codigoTarea, Map<String, Object> data) {
        Map<String,Object> filterTask = data.entrySet().stream()
                .filter(e-> ALLLOWED_COLUMNS.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));

        if(filterTask == null || filterTask.isEmpty()) return "1";
        StringBuilder sql = new StringBuilder("UPDATE public.\"task\" SET ");
        List<Object> params = new ArrayList<>();
        int i = 0;
        for(Map.Entry<String,Object> e : filterTask.entrySet()){
            if(i>0) sql.append(", ");
            sql.append(e.getKey()).append("= ?");
            params.add(e.getValue());
            i++;
        }
        sql.append(" WHERE task_cod = ?");
        params.add(codigoTarea);
        return String.valueOf(jdbcTemplate.update(sql.toString(),params.toArray()));
    }
}
