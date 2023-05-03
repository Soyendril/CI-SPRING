package com.example.springunitaire.service;

import com.example.springunitaire.model.TodoEntity;
import com.example.springunitaire.repository.TodoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoEntity> findAll() {
        var data = todoRepository.findAll();
        if (data.isEmpty()) {
            throw new RuntimeException("No data found");
        } else {
            return data;
        }
    }

    public Optional<TodoEntity> findById(String id) {
        var data =  todoRepository.findById(id);
        if (data.isEmpty()) {
            throw new RuntimeException("No data found");
        } else {
            return data;
        }
    }

    public TodoEntity save(TodoEntity entity) {
        return todoRepository.save(entity);
    }

    public TodoEntity update(String id, TodoEntity entity) {
        Optional<TodoEntity> optional = todoRepository.findById(id);
        if (optional.isPresent()) {
            TodoEntity existingEntity = optional.get();
            existingEntity.setTitle(entity.getTitle());
            existingEntity.setDone(entity.isDone());
            return todoRepository.save(existingEntity);
        } else {
            return null;
        }
    }

    public void deleteById(String id) {
        todoRepository.deleteById(id);
    }
}

