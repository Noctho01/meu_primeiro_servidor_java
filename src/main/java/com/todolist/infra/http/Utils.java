package com.todolist.infra.http;

public class Utils {

   public static String generateResponseBody(String statusMessage, String message, String data) {
      return String.format("""
        {
         "status": "%s",
         "message": "%s",
         "data": %s
        }
        """,
        statusMessage,
        message,
        data
      );
   }
}
