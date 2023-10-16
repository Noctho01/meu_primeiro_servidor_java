package com.todolist.repositories;

import com.todolist.domain.TodoList;

import java.io.IOException;

public interface IListRepository {
   long getId() throws IOException;
   boolean nameAlreadyExists(String name) throws IOException;
   void save(TodoList list) throws IOException;
   String getAll() throws IOException;
}
