package com.example.springunitaire.repository;

import com.example.springunitaire.model.TodoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends MongoRepository<TodoEntity, String> {
}
