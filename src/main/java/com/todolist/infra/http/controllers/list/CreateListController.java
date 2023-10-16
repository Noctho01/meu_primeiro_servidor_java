package com.todolist.infra.http.controllers.list;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.todolist.domain.exceptions.InvalidListDescriptionException;
import com.todolist.domain.exceptions.InvalidListNameException;
import com.todolist.dtos.CreationListDto;
import com.todolist.infra.http.Utils;
import com.todolist.services.exceptions.ListNameAlreadyExistsException;
import com.todolist.services.list.CreateListService;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class CreateListController implements HttpHandler {

   private final CreateListService createListService;

   public CreateListController(CreateListService createListService) {
      this.createListService = createListService;
   }

   @Override
   public void handle(HttpExchange exchange) throws IOException {
      OutputStream response = exchange.getResponseBody();
      InputStream request = exchange.getRequestBody();

      String responseBody = "";
      int statusCode = 0;

      try {
         BufferedReader reader = new BufferedReader(new InputStreamReader(request));
         StringBuilder stringBuilder = new StringBuilder();

         String inputLine;

         while ((inputLine = reader.readLine()) != null)
            stringBuilder.append(inputLine);

         request.close();
         reader.close();

         String responseBodyJson = stringBuilder.toString();

         Gson gson = new GsonBuilder().setPrettyPrinting().create();
         CreationListDto creationListDto = gson.fromJson(responseBodyJson, CreationListDto.class);

         try {
            createListService.execute(creationListDto);
            responseBody = Utils.generateResponseBody("todo-list created", null, null);
            statusCode = 201;

         } catch (ListNameAlreadyExistsException | InvalidListNameException |  InvalidListDescriptionException e) {
            responseBody = Utils.generateResponseBody("error", e.getMessage(), null);
            statusCode = 400;
         }

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
