package com.todolist.infra.database.json;

import java.io.*;

public class JsonPersistenceDb {
   public static String readFile(String fileName) throws IOException {
      FileReader reader;

      try {
         reader = new FileReader(fileName);

      } catch (FileNotFoundException e) {
         FileWriter writer = new FileWriter(fileName);
         writer.write("[]");
         writer.close();

         reader = new FileReader(fileName);
      }

      StringBuilder content = new StringBuilder();
      int character;

      while((character = reader.read()) != -1)
         content.append((char) character);

      reader.close();
      return content.toString();
   }

   public static void writeFile(String fileName, String content) throws IOException {
      FileWriter writer = new FileWriter(fileName);
      writer.write(content);
      writer.close();
   }
}
