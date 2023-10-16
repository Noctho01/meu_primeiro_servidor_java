package com.todolist;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.todolist.infra.http.adapters.controllers.ListControllersFactory;
import com.todolist.infra.http.core.Router;
import com.todolist.infra.http.core.Server;
import com.todolist.repositories.IListRepository;
import com.todolist.repositories.JsonListRepository;

public class Main {
   public static void main(String[] args) {
      String host = "localhost";
      int port = 8080;

      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      IListRepository listRepository = new JsonListRepository(gson);
      ListControllersFactory listControllersFactory = new ListControllersFactory(listRepository);

      Server server = new Server(new Router(listControllersFactory));
      server.init(host, port, "servidor iniciado na porta " + port);
   }
}