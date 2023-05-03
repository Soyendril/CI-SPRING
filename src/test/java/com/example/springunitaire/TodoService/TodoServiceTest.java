package com.example.springunitaire.TodoService;

import com.example.springunitaire.model.TodoEntity;
import com.example.springunitaire.repository.TodoRepository;
import com.example.springunitaire.service.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TodoServiceTest {

    @Autowired
    private TodoService todoService;

    @MockBean
    private TodoRepository todoRepository;

    @Test
    public void testFindAllWithNoData() {
        when(todoRepository.findAll()).thenReturn(Collections.emptyList());
        assertThrows(RuntimeException.class, () -> todoService.findAll());
    }

    @Test
    public void testFindAllWithData() {
        List<TodoEntity> todos = new ArrayList<>();
        TodoEntity todo1 = new TodoEntity();
        todo1.setId("1");
        todo1.setTitle("Task 1");
        todo1.setDone(false);
        todos.add(todo1);

        TodoEntity todo2 = new TodoEntity();
        todo2.setId("2");
        todo2.setTitle("Task 2");
        todo2.setDone(true);
        todos.add(todo2);

        when(todoRepository.findAll()).thenReturn(todos);

        List<TodoEntity> result = todoService.findAll();
        assertThat(result.size(), is(2));
        assertThat(result.get(0), is(todo1));
        assertThat(result.get(1), is(todo2));
    }

    @Test
    public void testFindByIdWithNoData() {
        String id = "1";
        when(todoRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> todoService.findById(id));
        //test
    }

    @Test
    public void testFindByIdWithData() {
        String id = "1";
        TodoEntity todo = new TodoEntity();
        todo.setId(id);
        todo.setTitle("Task 1");
        todo.setDone(false);

        when(todoRepository.findById(id)).thenReturn(Optional.of(todo));

        Optional<TodoEntity> result = todoService.findById(id);
        assertThat(result.isPresent(), is(true));
        assertThat(result.get(), is(todo));
    }

    @Test
    public void testUpdateWithNoData() {
        String id = "1";
        TodoEntity todo = new TodoEntity();
        todo.setTitle("Task 1");
        todo.setDone(false);

        when(todoRepository.findById(id)).thenReturn(Optional.empty());

        TodoEntity result = todoService.update(id, todo);
        assertThat(result, is(nullValue()));
    }

    @Test
    public void testUpdateWithData() {
        String id = "1";
        TodoEntity existingTodo = new TodoEntity();
        existingTodo.setId(id);
        existingTodo.setTitle("Task 1");
        existingTodo.setDone(false);

        TodoEntity newTodo = new TodoEntity();
        newTodo.setTitle("Updated task");
        newTodo.setDone(true);

        when(todoRepository.findById(id)).thenReturn(Optional.of(existingTodo));
        when(todoRepository.save(existingTodo)).thenReturn(existingTodo);

        TodoEntity result = todoService.update(id, newTodo);
        assertThat(result, is(existingTodo));
        assertThat(existingTodo.getTitle(), is("Updated task"));
        assertThat(existingTodo.isDone(), is(true));
    }

/*    @Test
    public void testSomething() {
        // Échoue le test délibérément
        fail("Ce test a échoué intentionnellement");
    }*/

    //retest
    // test branch 122
}
