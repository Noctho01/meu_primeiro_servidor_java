package com.todolist.infra.http.core;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.todolist.infra.http.adapters.controllers.ListControllersFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Router implements HttpHandler {

   private final ListControllersFactory listControllersFactory;

   public Router(ListControllersFactory listControllersFactory) {
      this.listControllersFactory = listControllersFactory;
   }

   @Override
   public void handle(HttpExchange exchange) throws IOException {

      Headers headers = exchange.getResponseHeaders();
      headers.add("Content-Type", "application/json; charset=UTF-8");

      switch (exchange.getRequestMethod()) {
         case "POST" -> postRouters(exchange);
         case "GET" -> getRouters(exchange);
         case "PUT" -> putRouters(exchange);
         case "DELETE" -> deleteRouters(exchange);
         default -> defaultHandler(exchange);
      }
   }

   private void postRouters(HttpExchange exchange) throws IOException {
      if (exchange.getRequestURI().toString().equals("/todolist")) {
         listControllersFactory.makeCreateListController().handle(exchange);
      } else {
         defaultHandler(exchange);
      }
   }

   private void getRouters(HttpExchange exchange) throws IOException {
      if (exchange.getRequestURI().toString().equals("/todolist")) {
         listControllersFactory.makeGetAllTodoListController().handle(exchange);
      } else {
         defaultHandler(exchange);
      }
   }

   private void putRouters(HttpExchange exchange) {
   }

   private void deleteRouters(HttpExchange exchange) {
   }

   private void defaultHandler(HttpExchange exchange) throws IOException {
      String defaultResponseBody = """
              {
               "server": "todolist",
               "version": "1.0.0",
               "developer": "Vinicius Rodrigues (vinicius.dev01@gmail.com)"
              }
              """;
      exchange.sendResponseHeaders(200, defaultResponseBody.length());
      OutputStream response = exchange.getResponseBody();
      response.write(defaultResponseBody.getBytes(StandardCharsets.UTF_8));
      response.close();
   }
}
