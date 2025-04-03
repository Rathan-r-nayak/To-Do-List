package com.example.controller;

import com.example.model.*;
import com.example.service.TaskService;

import java.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    TaskService service;


    @PostMapping("/add")
    public ResponseEntity<Task> addTask(@RequestBody Task task)
    {
        boolean res = service.saveTask(task);
        if(!res)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(task,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTask()
    {
        return new ResponseEntity<>(service.getAllTask(),HttpStatus.OK);
    }



    @GetMapping("/completed")
    public ResponseEntity<List<Task>> taskCompleted()
    {
        return new ResponseEntity<>(service.getCompleted(),HttpStatus.OK);
    }

    @GetMapping("/pending")
    @ResponseBody
    public ResponseEntity<List<Task>> taskPending()
    {
        return new ResponseEntity<>(service.getPending(),HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Task> updateTask(@RequestBody Task task)
    {
        boolean res = service.updateTask(task);
        if(!res)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(task,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer id)
    {
        service.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("togglestatus/{id}")
    public ResponseEntity<Task> toogleStatus(@PathVariable Integer id)
    {
        return new ResponseEntity<>(service.toggleStatus(id),HttpStatus.OK);
    }



    @GetMapping("/test")
        public String testEndpoint() {
            return "Spring MVC is running!";
        }
}
