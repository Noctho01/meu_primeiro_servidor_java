package com.todolist.services.exceptions;

public class ListNameAlreadyExistsException extends Exception {

   private final String name = "ListNameAlreadyExistsException";

   public ListNameAlreadyExistsException(String listName) {
      super("list name '" + listName + "' already exists");
   }

   public String getName() {
      return this.name;
   }
}
