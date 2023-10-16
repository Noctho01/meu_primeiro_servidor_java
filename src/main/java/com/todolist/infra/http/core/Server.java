package com.todolist.infra.http.core;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
   private final Router router;

   public Server(Router router) {
      this.router = router;
   }

   public void init(String host, int port, String serverMessage){
      try {
         HttpServer server = HttpServer.create(new InetSocketAddress(host, port), 0);
         server.createContext("/", router);
         server.start();
         System.out.println("server: " + server.getAddress());
         System.out.println(serverMessage);

      } catch (IOException e) {
         System.out.println("-- SERVER ERROR --");
         System.out.println(e.getMessage());
      }
   }
}
