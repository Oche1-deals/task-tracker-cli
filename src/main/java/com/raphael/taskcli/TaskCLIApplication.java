package com.raphael.taskcli;

import com.raphael.taskcli.service.TaskService;
import com.raphael.taskcli.storage.TaskStorage;

import java.util.List;

public class TaskCLIApplication {

    public static void main(String[] args) {

        TaskStorage storage = new TaskStorage();
        storage.createFileIfNotExists();

        TaskService service = new TaskService();

        if (args.length == 0) {
            System.out.println("No command provided.");
            return;
        }

        String command = args[0];

        // ADD TASK
        if (command.equals("add")) {

            if (args.length < 2) {
                System.out.println("Please provide task description.");
                return;
            }

            service.addTask(args[1]);
        }

        // LIST TASKS
        else if (command.equals("list")) {

            if (args.length == 1) {
                service.listTasks(null);
            } else {
                service.listTasks(args[1]);
            }
        }

        // MARK IN PROGRESS
        else if (command.equals("mark-in-progress")) {

            if (args.length < 2) {
                System.out.println("Please provide task ID.");
                return;
            }

            Integer taskId;

            try {
                taskId = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid task ID.");
                return;
            }

            service.markTaskInProgress(taskId);
        }

        // MARK DONE
        else if (command.equals("mark-done")) {

            if (args.length < 2) {
                System.out.println("Please provide task ID.");
                return;
            }

            Integer taskId;

            try {
                taskId = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid task ID.");
                return;
            }

            service.markTaskDone(taskId);
        }

        // UPDATE TASK
        else if (command.equals("update")) {

            if (args.length < 3) {
                System.out.println("Please provide task ID and new description.");
                return;
            }

            Integer taskId;

            try {
                taskId = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid task ID.");
                return;
            }

            service.updateTask(taskId, args[2]);
        }

        // DELETE TASK
        else if (command.equals("delete")) {

            if (args.length < 2) {
                System.out.println("Please provide task ID.");
                return;
            }

            Integer taskId;

            try {
                taskId = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid task ID.");
                return;
            }

            service.deleteTask(taskId);
        }

        // HELP COMMAND
        else if (command.equals("help")) {

            System.out.println("""
                Commands:
                add \"description\"
                list
                list done
                list todo
                list in-progress
                update <id> \"new description\"
                delete <id>
                mark-done <id>
                mark-in-progress <id>
            """);
        }

        // UNKNOWN COMMAND
        else {
            System.out.println("Unknown command. Type 'help' for available commands.");
        }
    }
}