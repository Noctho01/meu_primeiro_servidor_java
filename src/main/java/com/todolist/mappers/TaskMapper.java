package com.todolist.mappers;

import com.todolist.domain.Task;
import com.todolist.dtos.TaskDto;

public class TaskMapper {

   public static TaskDto toDto(Task task) {
      return new TaskDto(
              task.getId(),
              task.getResume(),
              task.getCreatedAt(),
              task.getCompletedAt()
      );
   }
}
