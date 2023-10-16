package com.todolist.domain.exceptions;

public class InvalidListNameException extends Exception {

   private final String name = "InvalidListNameException";

   public InvalidListNameException(String invalidName) {
      super("list name '" + invalidName + "' is invalid or not provided");
   }
}
