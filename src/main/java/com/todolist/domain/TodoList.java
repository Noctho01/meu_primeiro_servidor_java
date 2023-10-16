package com.todolist.domain;

import com.todolist.domain.exceptions.InvalidListDescriptionException;
import com.todolist.domain.exceptions.InvalidListNameException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class TodoList {

   private final long id;
   private String name;
   private String description;
   private ArrayList<Task> tasks;

   public TodoList(long id, String name, String description) throws InvalidListNameException, InvalidListDescriptionException {
      if (name == null || name.isEmpty() || name.length() > 250)
         throw new InvalidListNameException(name);

      if (description == null || description.isEmpty() || description.length() > 500)
         throw new InvalidListDescriptionException(description);

      this.id = id;
      this.name = name;
      this.description = description;
      this.tasks = new ArrayList<>();
   }

   public TodoList(long id, String name, String description, ArrayList<Task> tasks) throws InvalidListNameException, InvalidListDescriptionException {
      this(id, name, description);
      this.tasks = tasks;
   }

   public long getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public String getDescription() {
      return description;
   }

   public ArrayList<Task> getTasks() {
      return tasks;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public void addNewTask(Task newTask) {
      tasks.add(newTask);
   }

   public void removeTask(long taskId) {
      tasks.removeIf(task -> task.getId() == taskId);
   }
}
