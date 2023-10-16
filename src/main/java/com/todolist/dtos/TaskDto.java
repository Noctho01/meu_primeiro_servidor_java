package com.todolist.dtos;

import java.util.Date;

public record TaskDto(
        long id,
        String resume,
        Date createdAt,
        Date completedAt
) {
}
