package com.example.springunitaire.controller;

import com.example.springunitaire.model.TodoEntity;
import com.example.springunitaire.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping()
    public List<TodoEntity> findAll() {
        return todoService.findAll();
    }

    @GetMapping("{id}")
    public TodoEntity findById(String id) {
        return todoService.findById(id).orElse(null);
    }

    @PostMapping()
    public TodoEntity save(TodoEntity todoEntity) {
        return todoService.save(todoEntity);
    }

    @DeleteMapping("{id}")
    public void deleteById(String id) {
        todoService.deleteById(id);
    }

    @PutMapping("{id}")
    public TodoEntity update(String id, TodoEntity todoEntity) {
        return todoService.update(id, todoEntity);
    }


}
