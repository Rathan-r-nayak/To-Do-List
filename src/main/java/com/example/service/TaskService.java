package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.example.model.*;
import com.example.dao.TaskDAO;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TaskService {
    @Autowired
    private TaskDAO taskDAO;
    int curId = 5;

    @Transactional
    public boolean saveTask(Task task) {
        if (task.getName() == null || task.getName().trim().isEmpty()) {
            return false;
        }
        taskDAO.saveTask(task);
        curId++;
        return true;
    }

    public List<Task> getAllTask() {
        return taskDAO.getAllTasks();
    }

    public List<Task> getCompleted()
    {
        return taskDAO.getCompleted();
    }
    public List<Task> getPending()
    {
        return taskDAO.getPending();
    }
    public boolean updateTask(Task task) {
        if (task.getId() <= 0) {
            return false;
        }
        taskDAO.updateTask(task);
        return true;
    }

    public boolean deleteTask(int id) {
        taskDAO.deleteTask(id);
        return true;
    }
    public Task toggleStatus(Integer id)
    {
        Task task = null;
        if(id <= 0 || id > curId)
        {
            return null;
        }
        task = taskDAO.getTaskById(id);
        if(task != null)
        {
            task.setStatus(!task.getStatus());
            taskDAO.updateTask(task);
        }
        return task;
    }
}
