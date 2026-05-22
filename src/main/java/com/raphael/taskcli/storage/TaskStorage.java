package com.raphael.taskcli.storage;

import com.raphael.taskcli.model.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskStorage {

    private static final String FILE_NAME = "tasks.json";

    public void createFileIfNotExists() {

        File file = new File(FILE_NAME);

        try {
            if (file.createNewFile()) {
                FileWriter writer = new FileWriter(FILE_NAME);
                writer.write("[]");
                writer.close();
                System.out.println("tasks.json file created.");
            }
        } catch (IOException e) {
            System.out.println("Error creating file.");
        }
    }

    // READ TASKS FROM FILE
    public List<Task> loadTasks() {

        List<Task> tasks = new ArrayList<>();

        try {

            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line.trim());
            }

            reader.close();

            String content = sb.toString();

            if (content.equals("[]")) {
                return tasks;
            }

            // REMOVE STARTING [ AND ENDING ]
            content = content.substring(1, content.length() - 1);

            // SPLIT TASK OBJECTS
            String[] taskObjects = content.split("\\},\\s*\\{");

            for (String taskObject : taskObjects) {

                // CLEAN OBJECT
                taskObject = taskObject.replace("{", "")
                        .replace("}", "");

                String[] fields = taskObject.split(",");

                Task task = new Task();

                for (String field : fields) {

                    String[] keyValue = field.split(":", 2);

                    String key = keyValue[0]
                            .replace("\"", "")
                            .trim();

                    String value = keyValue[1]
                            .replace("\"", "")
                            .trim();

                    switch (key) {

                        case "id":
                            task.setId(Integer.parseInt(value));
                            break;

                        case "description":
                            task.setDescription(value);
                            break;

                        case "status":
                            task.setStatus(value);
                            break;

                        case "createdAt":
                            task.setCreatedAt(value);
                            break;

                        case "updatedAt":
                            task.setUpdatedAt(value);
                            break;
                    }
                }

                tasks.add(task);
            }

        } catch (Exception e) {

            System.out.println("Error reading tasks: " + e.getMessage());
        }

        return tasks;
    }

    // SAVE ALL TASKS
    public void saveAllTasks(List<Task> tasks) {

        try {
            FileWriter writer = new FileWriter(FILE_NAME);

            StringBuilder json = new StringBuilder();
            json.append("[\n");

            for (int i = 0; i < tasks.size(); i++) {

                Task task = tasks.get(i);

                json.append("  {\n")
                        .append("    \"id\": ").append(task.getId()).append(",\n")
                        .append("    \"description\": \"").append(task.getDescription()).append("\",\n")
                        .append("    \"status\": \"").append(task.getStatus()).append("\",\n")
                        .append("    \"createdAt\": \"").append(task.getCreatedAt()).append("\",\n")
                        .append("    \"updatedAt\": \"").append(task.getUpdatedAt()).append("\"\n")
                        .append("  }");

                if (i < tasks.size() - 1) {
                    json.append(",");
                }

                json.append("\n");
            }

            json.append("]");

            writer.write(json.toString());
            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }
}