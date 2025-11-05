package com.neha.ToDoList.dao;

import com.neha.ToDoList.exception.InvalidTaskException;
import com.neha.ToDoList.exception.TaskNotFoundException;
import com.neha.ToDoList.model.Task;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.neha.ToDoList.utils.DateMethods.compareDate;


public class ToDoDaoMySqlImpl implements ToDoDao{
    private static Connection conn;

    public ToDoDaoMySqlImpl() throws SQLException {
        if(conn == null){
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist", "root", "root");
            PreparedStatement ptst = conn.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS tasks (" +
                            "task_id INT AUTO_INCREMENT PRIMARY KEY, " +
                            "task_name VARCHAR(255), " +
                            "task_deadLine DATE, " +
                            "task_isDone BOOLEAN" +
                            ")"
            );
            System.out.println("✅ MySQL connection established!");
            ptst.executeUpdate();
        }
    }

    @Override
    public List<Task> getTaskLists() throws SQLException {
        List<Task> tasksList = new ArrayList<>();
        String query = "SELECT * FROM tasks";
        PreparedStatement ptst = conn.prepareStatement(query);
        ResultSet rs = ptst.executeQuery();

        while(rs.next()){
            Task t = new Task(
                rs.getString("task_name"),
                rs.getInt("task_id"),
                rs.getDate("task_deadLine").toLocalDate(),
                rs.getBoolean("task_isDone")
            );
            tasksList.add(t);
        }
        return tasksList;
    }

    @Override
    public List<Task> addBatchTask(List<Task> new_tasks) throws SQLException {
        for(Task t : new_tasks){
            String name = t.getName();
            int id = t.getId();
            LocalDate deadLine = t.getDeadline();
            Boolean isDone = t.getIsDone();
            String query = "INSERT INTO tasks (task_id, task_name, task_deadLine, task_isDone) VALUES(?,?,?,?)";
            PreparedStatement ptst = conn.prepareStatement(query);
            ptst.setInt(1,id);
            ptst.setString(2,name);
            ptst.setDate(3, Date.valueOf(deadLine));
            ptst.setBoolean(4, isDone);
            ptst.executeUpdate();
        }
        return new_tasks;
    }

    @Override
    public Task getTask(int id) throws InvalidTaskException, TaskNotFoundException, SQLException {
        String query = "Select * FROM tasks WHERE task_id="+id;
        PreparedStatement ptst = conn.prepareStatement(query);
        ResultSet rs = ptst.executeQuery();
        Task t = new Task();
        if(rs.next()){
            t.setName(rs.getString("task_name"));
            t.setId(rs.getInt("task_id"));
            t.setDeadline(rs.getDate("task_deadLine").toLocalDate());
            t.setIsDone(rs.getBoolean("task_isDone"));
        }
        else{
            throw new TaskNotFoundException("Task not found");
        }
        return t;
    }

    @Override
    public Task deleteTask(int id) throws InvalidTaskException, TaskNotFoundException, SQLException {
        Task t = getTask(id);
        String query = "DELETE FROM tasks WHERE task_id="+id;
        PreparedStatement ptst = conn.prepareStatement(query);
        ptst.executeUpdate();
        return t;
    }

    @Override
    public Task updateTask(int id, Task updatedTask) throws InvalidTaskException, TaskNotFoundException, SQLException {
        Task t = getTask(id);
        Task utask = updatedTask;

        if(updatedTask.getName()!=null){
            String query = "UPDATE tasks SET task_name=? WHERE task_id=?";
            PreparedStatement psts = conn.prepareStatement(query);
            psts.setString(1, updatedTask.getName());
            utask.setName(updatedTask.getName());
            psts.setInt(2,id);
            psts.executeUpdate();
        }
        if(updatedTask.getDeadline()!=null){
            String query = "UPDATE tasks SET task_deadLine=? WHERE task_id=?";
            PreparedStatement psts = conn.prepareStatement(query);
            psts.setDate(1, Date.valueOf(updatedTask.getDeadline()));
            utask.setDeadline(updatedTask.getDeadline());
            psts.setInt(2,id);
            psts.executeUpdate();
        }
        if(updatedTask.getIsDone()!=null){
            String query = "UPDATE tasks SET task_isDone=? WHERE task_id=?";
            PreparedStatement psts = conn.prepareStatement(query);
            psts.setBoolean(1, updatedTask.getIsDone());
            utask.setIsDone(updatedTask.getIsDone());
            psts.setInt(2,id);
            psts.executeUpdate();
        }
        utask.setId(id);
        return utask;
    }

    @Override
    public List<Task> updateBatchTask(List<Task> updatedTask) throws InvalidTaskException, TaskNotFoundException, SQLException {
        List<Task> ubtasks = new ArrayList<>();
        Boolean check = false;
        String query = "SELECT * FROM tasks WHERE task_id = ";
        for(Task t : updatedTask){
            int id = t.getId();
            PreparedStatement ptst = conn.prepareStatement(query+id);
            ResultSet rs = ptst.executeQuery();
            if(!rs.next()){
                throw new TaskNotFoundException("Task not found");
            }
        }

        for(Task t : updatedTask){
            int id = t.getId();
            Task ut = new Task(t.getName(), t.getId(), t.getDeadline(), t.getIsDone());
            ubtasks.add(updateTask(id, ut));
        }
        return ubtasks;
    }

    @Override
    public List<Task> search(String name) throws SQLException {
        List<Task> serachedTasks = new ArrayList<>();
        String query = "SELECT * FROM tasks WHERE task_name LIKE ?";
        PreparedStatement ptst = conn.prepareStatement(query);
        ptst.setString(1, "%" + name + "%");
        ResultSet rs = ptst.executeQuery();

        while(rs.next()){
            Task t = new Task(
                    rs.getString("task_name"),
                    rs.getInt("task_id"),
                    rs.getDate("task_deadLine").toLocalDate(),
                    rs.getBoolean("task_isDone")
            );
            serachedTasks.add(t);
        }
        return serachedTasks;
    }

    @Override
    public List<Task> sort(String field, int desc) throws SQLException {
        List<Task> sortedTasks = new ArrayList<>();
        if (!field.equals("task_name") && !field.equals("task_deadLine")) {
            field = "task_deadLine";
        }

        String direction = (desc == 1) ? "DESC" : "ASC";

        String query = "SELECT * FROM tasks ORDER BY " + field + " " + direction;

        PreparedStatement ptst = conn.prepareStatement(query);

        ResultSet rs = ptst.executeQuery();

        while(rs.next()){
            Task t = new Task(
                    rs.getString("task_name"),
                    rs.getInt("task_id"),
                    rs.getDate("task_deadLine").toLocalDate(),
                    rs.getBoolean("task_isDone")
            );
            sortedTasks.add(t);
        }
        return sortedTasks;
    }

    @Override
    public List<Task> filter(String name, LocalDate deadLine, Boolean isDone) throws SQLException, TaskNotFoundException {

        List<Task> filteredTasks = new ArrayList<>();

        if(name!=null && deadLine==null && isDone==null){
            String query = "SELECT * FROM tasks WHERE task_name LIKE ?";
            PreparedStatement ptst = conn.prepareStatement(query);
            ptst.setString(1, "%" + name + "%");
            ResultSet rs = ptst.executeQuery();

            while(rs.next()){
                Task t = new Task(
                        rs.getString("task_name"),
                        rs.getInt("task_id"),
                        rs.getDate("task_deadLine").toLocalDate(),
                        rs.getBoolean("task_isDone")
                );
                filteredTasks.add(t);
            }
        }
        else if(name==null && deadLine!=null && isDone==null){
            String query = "SELECT * FROM tasks WHERE task_deadLine = ?";
            PreparedStatement ptst = conn.prepareStatement(query);
            ptst.setDate(1, Date.valueOf(deadLine));
            ResultSet rs = ptst.executeQuery();

            while(rs.next()){
                Task t = new Task(
                        rs.getString("task_name"),
                        rs.getInt("task_id"),
                        rs.getDate("task_deadLine").toLocalDate(),
                        rs.getBoolean("task_isDone")
                );
                filteredTasks.add(t);
            }
        }
        else if(name==null && deadLine==null && isDone!=null){
            String query = "SELECT * FROM tasks WHERE task_isDone = ?";
            PreparedStatement ptst = conn.prepareStatement(query);
            ptst.setBoolean(1, isDone);
            ResultSet rs = ptst.executeQuery();

            while(rs.next()){
                Task t = new Task(
                        rs.getString("task_name"),
                        rs.getInt("task_id"),
                        rs.getDate("task_deadLine").toLocalDate(),
                        rs.getBoolean("task_isDone")
                );
                filteredTasks.add(t);
            }
        }
        else if(name!=null && deadLine!=null && isDone==null){
            String query = "SELECT * FROM tasks WHERE task_name LIKE ? AND task_deadLine = ?";
            PreparedStatement ptst = conn.prepareStatement(query);
            ptst.setString(1, "%" + name + "%");
            ptst.setDate(2, Date.valueOf(deadLine));
            ResultSet rs = ptst.executeQuery();

            while(rs.next()){
                Task t = new Task(
                        rs.getString("task_name"),
                        rs.getInt("task_id"),
                        rs.getDate("task_deadLine").toLocalDate(),
                        rs.getBoolean("task_isDone")
                );
                filteredTasks.add(t);
            }
        }
        else if(name!=null && deadLine==null && isDone!=null){
            String query = "SELECT * FROM tasks WHERE task_name LIKE ? AND task_isDone = ?";
            PreparedStatement ptst = conn.prepareStatement(query);
            ptst.setString(1, "%" + name + "%");
            ptst.setBoolean(2, isDone);
            ResultSet rs = ptst.executeQuery();

            while(rs.next()){
                Task t = new Task(
                        rs.getString("task_name"),
                        rs.getInt("task_id"),
                        rs.getDate("task_deadLine").toLocalDate(),
                        rs.getBoolean("task_isDone")
                );
                filteredTasks.add(t);
            }
        }
        else if(name==null && deadLine!=null && isDone!=null){
            String query = "SELECT * FROM tasks WHERE task_deadLine = ? AND task_isDone = ?";
            PreparedStatement ptst = conn.prepareStatement(query);
            ptst.setString(1, String.valueOf(deadLine));
            ptst.setBoolean(2, isDone);
            ResultSet rs = ptst.executeQuery();

            while(rs.next()){
                Task t = new Task(
                        rs.getString("task_name"),
                        rs.getInt("task_id"),
                        rs.getDate("task_deadLine").toLocalDate(),
                        rs.getBoolean("task_isDone")
                );
                filteredTasks.add(t);
            }
        }
        else if(name!=null && deadLine!=null && isDone!=null){
            String query = "SELECT * FROM tasks WHERE task_name LIKE ? AND task_deadLine = ? AND task_isDone = ?";
            PreparedStatement ptst = conn.prepareStatement(query);
            ptst.setString(1, "%" + name + "%");
            ptst.setDate(2, Date.valueOf(deadLine));
            ptst.setBoolean(3, isDone);
            ResultSet rs = ptst.executeQuery();

            while(rs.next()){
                Task t = new Task(
                        rs.getString("task_name"),
                        rs.getInt("task_id"),
                        rs.getDate("task_deadLine").toLocalDate(),
                        rs.getBoolean("task_isDone")
                );
                filteredTasks.add(t);
            }
        }
        else{
            throw new TaskNotFoundException("Task not found");
        }

        return filteredTasks;
    }
}
