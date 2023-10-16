package com.todolist.services.list;

import com.todolist.repositories.IListRepository;
import com.todolist.domain.TodoList;
import com.todolist.domain.exceptions.InvalidListDescriptionException;
import com.todolist.domain.exceptions.InvalidListNameException;
import com.todolist.dtos.CreationListDto;
import com.todolist.services.exceptions.ListNameAlreadyExistsException;

import java.io.IOException;

public class CreateListService {

   private final IListRepository listRepository;

   public CreateListService(IListRepository listRepository) {
      this.listRepository = listRepository;
   }

   public void execute(CreationListDto dto) throws ListNameAlreadyExistsException, InvalidListDescriptionException, InvalidListNameException, IOException {
      boolean nameAlreadyExists = this.listRepository.nameAlreadyExists(dto.listName());

      if (nameAlreadyExists)
         throw new ListNameAlreadyExistsException(dto.listName());

      long newTodoListId = this.listRepository.getId();

      TodoList newList = new TodoList(
              newTodoListId,
              dto.listName(),
              dto.listDescription()
      );

      this.listRepository.save(newList);
   }
}
