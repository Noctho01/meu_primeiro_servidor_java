package com.todolist.mappers;

import com.todolist.domain.TodoList;
import com.todolist.dtos.TodoListDto;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TodoListMapper {

   public static TodoListDto toDto(TodoList todoList) {
      return new TodoListDto(
              todoList.getId(),
              todoList.getName(),
              todoList.getDescription(),
              todoList.getTasks().stream().map(TaskMapper::toDto).collect(Collectors.toCollection(ArrayList::new))
      );
   }
}
