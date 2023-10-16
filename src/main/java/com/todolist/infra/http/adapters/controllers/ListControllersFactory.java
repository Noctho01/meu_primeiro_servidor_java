package com.todolist.infra.http.adapters.controllers;

import com.todolist.infra.http.controllers.list.CreateListController;
import com.todolist.infra.http.controllers.list.GetAllTodoListsController;
import com.todolist.repositories.IListRepository;
import com.todolist.services.list.CreateListService;
import com.todolist.services.list.GetTodoListsService;

public class ListControllersFactory {

   private final IListRepository listRepository;

   public ListControllersFactory(IListRepository listRepository) {
      this.listRepository = listRepository;
   }

   public CreateListController makeCreateListController() {
      CreateListService service = new CreateListService(listRepository);
      return new CreateListController(service);
   }

   public GetAllTodoListsController makeGetAllTodoListController() {
      GetTodoListsService service = new GetTodoListsService(listRepository);
      return new GetAllTodoListsController(service);
   }
}
