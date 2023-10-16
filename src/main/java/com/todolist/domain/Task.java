package com.todolist.domain;

import java.util.Date;

public class Task {

   private final long id;
   private final String resume;
   private final Date createdAt;
   private Date completedAt;

   public Task(int id, String resume, Date createdAt) {
      this.id = id;
      this.resume = resume;
      this.createdAt = createdAt;
   }

   public Task(int id, String resume, Date createdAt, Date completedAt) {
      this(id, resume, createdAt);
      this.completedAt = completedAt;
   }

   public long getId() {
      return id;
   }

   public String getResume() {
      return resume;
   }

   public Date getCreatedAt() {
      return createdAt;
   }

   public Date getCompletedAt() {
      return completedAt;
   }

   public boolean isCompleted() {
      return completedAt != null;
   }

   public void complete() {
      completedAt = new Date();
   }
}
