package com.todolist.dtos;

import java.util.ArrayList;

public record TodoListDto(
        long id,
        String name,
        String description,
        ArrayList<TaskDto> tasks
) {
}
