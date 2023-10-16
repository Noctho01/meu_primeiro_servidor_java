package com.todolist.repositories;

import static com.todolist.infra.database.json.JsonPersistenceDb.*;

import com.google.gson.reflect.TypeToken;
import com.todolist.domain.TodoList;
import com.google.gson.Gson;
import com.todolist.dtos.TodoListDto;
import com.todolist.mappers.TodoListMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class JsonListRepository implements IListRepository {

   private final String fileName = "lists.json";
   private final Gson jsonReader;

   public JsonListRepository(Gson jsonReader) {
      this.jsonReader = jsonReader;
   }

   private ArrayList<TodoListDto> getData() throws IOException {
      String jsonFile = readFile(this.fileName);
      final var typeToken = new TypeToken<ArrayList<TodoListDto>>() {}.getType();
      return jsonReader.fromJson(jsonFile, typeToken);
   }

   @Override
   public long getId() throws IOException {
      ArrayList<TodoListDto> todoLists = getData();
      long lastTodoListId;

      if (todoLists.isEmpty()) lastTodoListId = 0;
      else lastTodoListId = todoLists.get(todoLists.size() - 1).id();

      return lastTodoListId + 1;
   }

   @Override
   public boolean nameAlreadyExists(String name) throws IOException {
      ArrayList<TodoListDto> todoLists = getData();

      Optional<TodoListDto> alreadyExists = todoLists.stream()
              .filter(todoList -> todoList.name().equals(name))
              .findFirst();

      return alreadyExists.isPresent();
   }

   @Override
   public void save(TodoList list) throws IOException {
      ArrayList<TodoListDto> todoLists = getData();

      todoLists.add(TodoListMapper.toDto(list));

      String newJsonFile = jsonReader.toJson(todoLists);
      writeFile(fileName, newJsonFile);
   }

   @Override
   public String getAll() throws IOException {
      return readFile(this.fileName);
   }
}
