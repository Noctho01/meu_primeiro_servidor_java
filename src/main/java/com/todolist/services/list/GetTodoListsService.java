package com.todolist.services.list;

import com.todolist.repositories.IListRepository;

import java.io.IOException;

public class GetTodoListsService {

   private final IListRepository listRepository;

   public GetTodoListsService(IListRepository listRepository) {
      this.listRepository = listRepository;
   }

   public String execute() throws IOException {
      return this.listRepository.getAll();
   }
}
