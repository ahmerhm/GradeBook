# Gradebook

## Description
The **Gradebook Management System** is a Java-based application designed to manage and track students' academic performance. It allows users to store and manipulate student data, including grades for various assignments, quizzes, midterms, and final exams. The program provides the flexibility to handle multiple students, ensuring accurate grade tracking and efficient management of student information.

## Project Status
In Progress -- needs updated on grade calculation as this is just the foundational code of GUI and logic.

## Features
- Store student information: name, letter grade, percentage, and scores for homeworks, quizzes, midterms, and finals.
- Validation of grade inputs to ensure values are within acceptable ranges (-1 to 100).
- Automatic grade calculation based on assignment scores.
- Flexible input for students' grade information.
- Supports multiple letter grades (`A+`, `A`, `A-`, etc.).

## Installation
To set up and run the project locally, follow these steps:

1. **Clone the repository:**
    ```bash
    git clone <repository-url>
    ```
2. **Navigate to the project directory:**
    ```bash
    cd Gradebook
    ```
3. **Compile the Java code:**
    ```bash
    javac Gradebook/*.java
    ```
4. **Run the main class:**
    ```bash
    java Gradebook.Main
    ```

## Usage
After compiling and running the program, the application will prompt you to enter student data. You can add multiple students, view their grades, and calculate their final grades.

The `Student` class encapsulates the details and behavior of a student, including their name, letter grade, percentage score, and individual scores for various academic components like homework, quizzes, and exams.

Sample student data:
```text
Abram Kart, B, 84, 89, 73, 67, 99, 85, 92
John Doe, A, 95, 100, 97, 92, 95, 97, 90
Ahmer, A, 100, 100, 100, 100, 100, 100, 100
