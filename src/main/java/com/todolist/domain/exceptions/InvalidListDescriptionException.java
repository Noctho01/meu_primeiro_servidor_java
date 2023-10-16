package com.todolist.domain.exceptions;

public class InvalidListDescriptionException extends Exception{

   private final String name = "InvalidListDescriptionException";

   public InvalidListDescriptionException(String invalidDescription) {
      super("list description '" + invalidDescription + "' is invalid or not provided");
   }
}
