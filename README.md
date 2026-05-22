# Task Tracker CLI

A simple command-line Task Tracker application built in Java.  
It allows users to manage tasks directly from the terminal and stores data persistently in a local JSON file.

---

## Features

- Add new tasks
- Update existing tasks
- Delete tasks
- Mark tasks as:
    - todo
    - in-progress
    - done
- List all tasks
- Filter tasks by status
- Persistent storage using JSON file (no external libraries)

---

## Tech Stack

- Java
- File I/O (java.io)
- OOP Principles
- CLI (Command Line Interface)

---

## Project Structure


task-cli/
├── src/
│ └── main/java/com/raphael/taskcli/
│ ├── model/
│ │ └── Task.java
│ ├── service/
│ │ └── TaskService.java
│ ├── storage/
│ │ └── TaskStorage.java
│ └── TaskCLIApplication.java
├── tasks.json
├── pom.xml
└── README.md


---

## How to Run

### 1. Compile the project

```bash
javac -d out src/main/java/com/raphael/taskcli/*.java
2. Run the application
java -cp out com.raphael.taskcli.TaskCLIApplication <command>
Available Commands
Add a task
add "Buy groceries"
List all tasks
list
List by status
list done
list todo
list in-progress
Update task
update 1 "New description"
Delete task
delete 1
Mark task status
mark-in-progress 1
mark-done 1
Data Storage

All tasks are stored in:

tasks.json

Example format:

[
  {
    "id": 1,
    "description": "Buy groceries",
    "status": "todo",
    "createdAt": "2026-05-22T10:00:00",
    "updatedAt": "2026-05-22T10:00:00"
  }
]
Key Learnings
CLI application design
File-based persistence
OOP architecture
CRUD operations
Manual JSON handling
Layered backend structure
Future Improvements
Add database (MySQL/PostgreSQL)
Add Spring Boot REST API version
Add user authentication
Add priority levels
Improve JSON parsing with libraries

Author
Raphael Odoh

