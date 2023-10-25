package dao;

import io.jsondb.JsonDBTemplate;
import model.Task;

import java.security.GeneralSecurityException;
import java.util.List;
import java.util.UUID;

public class TaskDao {

    // Directory to store data files
    private final String dbFilesLocation = System.getProperty("user.home") + "/tma";
    private final JsonDBTemplate jsonDBTemplate = new JsonDBTemplate(dbFilesLocation, "model");

    public Task getById(String id){
        return jsonDBTemplate.findById(id, Task.class);
    }

    public List<Task> getAll(){
        return jsonDBTemplate.findAll(Task.class);
    }

    public void upsert(String id, String name, String priority, String dueDate, String description) {
        Task updateTask = new Task();
        updateTask.setId(id);
        updateTask.setName(name);
        updateTask.setPriority(priority);
        updateTask.setDueDate(dueDate);
        updateTask.setDescription(description);
        jsonDBTemplate.upsert(updateTask);
    }

    public void add(String name, String priority, String dueDate, String description) {
        Task newTask = new Task();

        UUID uuid = UUID.randomUUID();

        newTask.setId(uuid.toString());
        newTask.setName(name);
        newTask.setPriority(priority);
        newTask.setDueDate(dueDate);
        newTask.setDescription(description);
        jsonDBTemplate.insert(newTask);
    }

    public void delete(String id) {
        Task t = new Task();
        t.setId(id);
        jsonDBTemplate.remove(t, Task.class);
    }


    public TaskDao() {
    }
}
