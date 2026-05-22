package com.raphael.taskcli.service;

import com.raphael.taskcli.model.Task;
import com.raphael.taskcli.storage.TaskStorage;

import java.time.LocalDateTime;
import java.util.List;

public class TaskService {

    private TaskStorage storage;

    public TaskService() {
        this.storage = new TaskStorage();
    }

    public void addTask(String description) {

        List<Task> tasks = storage.loadTasks();

        int newId = 1;

        for (Task t : tasks) {

            if (t.getId() >= newId) {
                newId = t.getId() + 1;
            }
        }

        LocalDateTime now = LocalDateTime.now();

        Task task = new Task(
                newId,
                description,
                "todo",
                now.toString(),
                now.toString()
        );

        tasks.add(task);

        storage.saveAllTasks(tasks);

        System.out.println("Task added successfully (ID: " + newId + ")");
    }
    public void listTasks(String status) {

        List<Task> tasks = storage.loadTasks();

        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }

        // LIST ALL TASKS
        if (status == null) {

            for (Task task : tasks) {
                System.out.println(task);
            }

            return;
        }

        // FILTER BY STATUS
        boolean found = false;

        for (Task task : tasks) {

            if (task.getStatus().equals(status)) {

                System.out.println(task);

                found = true;
            }
        }

        if (!found) {
            System.out.println("No tasks with status: " + status);
        }
    }
    public void markTaskInProgress(int taskId) {

        List<Task> tasks = storage.loadTasks();

        boolean found = false;

        for (Task task : tasks) {

            if (task.getId() == taskId) {

                task.setStatus("in-progress");

                task.setUpdatedAt(LocalDateTime.now().toString());

                found = true;

                break;
            }
        }

        if (found) {

            storage.saveAllTasks(tasks);

            System.out.println("Task marked as in-progress.");

        } else {

            System.out.println("Task not found.");
        }
    }
    public void markTaskDone(int taskId) {

        List<Task> tasks = storage.loadTasks();

        boolean found = false;

        for (Task task : tasks) {

            if (task.getId() == taskId) {

                task.setStatus("done");

                task.setUpdatedAt(LocalDateTime.now().toString());

                found = true;

                break;
            }
        }

        if (found) {

            storage.saveAllTasks(tasks);

            System.out.println("Task marked as done.");

        } else {

            System.out.println("Task not found.");
        }
    }
    public void updateTask(int taskId, String newDescription) {

        List<Task> tasks = storage.loadTasks();

        boolean found = false;

        for (Task task : tasks) {

            if (task.getId() == taskId) {

                task.setDescription(newDescription);

                task.setUpdatedAt(LocalDateTime.now().toString());

                found = true;

                break;
            }
        }

        if (found) {

            storage.saveAllTasks(tasks);

            System.out.println("Task updated successfully.");

        } else {

            System.out.println("Task not found.");
        }
    }
    public void deleteTask(int taskId) {

        List<Task> tasks = storage.loadTasks();

        boolean removed = false;

        for (int i = 0; i < tasks.size(); i++) {

            if (tasks.get(i).getId() == taskId) {

                tasks.remove(i);

                removed = true;

                break;
            }
        }

        if (removed) {

            storage.saveAllTasks(tasks);

            System.out.println("Task deleted successfully.");

        } else {

            System.out.println("Task not found.");
        }
    }

}