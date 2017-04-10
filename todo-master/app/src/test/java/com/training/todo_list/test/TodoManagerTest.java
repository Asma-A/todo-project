package com.training.todo_list.test;

import com.training.todo_list.R;
import com.training.todo_list.model.managers.TodoManager;
import com.training.todo_list.model.models.Todo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by asma.achour on 10/04/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class TodoManagerTest {

    @Mock
    private Set<Todo> mTodos = new HashSet<>();
    @Mock
    private Todo mTodo;

    @Before
    public void setUp() throws Exception {
        TodoManager manager = new TodoManager();
    }

    @Test
    public void toDosIsNotNull() {
        assertNotNull(mTodos);
    }
}