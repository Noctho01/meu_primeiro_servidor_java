package com.todolist.infra.http.controllers.list;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.todolist.infra.http.Utils;
import com.todolist.services.list.GetTodoListsService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class GetAllTodoListsController implements HttpHandler {

   private final GetTodoListsService getTodoListsService;

   public GetAllTodoListsController(GetTodoListsService getTodoListsService) {
      this.getTodoListsService = getTodoListsService;
   }

   @Override
   public void handle(HttpExchange exchange) throws IOException {
      OutputStream response = exchange.getResponseBody();
      InputStream request = exchange.getRequestBody();

      String responseBody = "";
      int statusCode = 0;

      try {
         String data = getTodoListsService.execute();
         responseBody = Utils.generateResponseBody("ok", null, data);
         statusCode = 200;

      } catch (Exception e) {
         responseBody = Utils.generateResponseBody("error", "ocorreu um erro no sevidor", null);
         statusCode = 500;
         e.printStackTrace();

      } finally {
         byte[] responseBodyBytes = responseBody.getBytes(StandardCharsets.UTF_8);
         exchange.sendResponseHeaders(statusCode, responseBodyBytes.length);
         response.write(responseBodyBytes);
         response.close();
      }
   }
}
