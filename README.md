# 📚 Console-Based Library Management System

## 📖 Overview
This is a simple console-based Library Management System implemented in Java. It provides basic library operations: adding and viewing books, registering members, borrowing and returning books, overdue detection, and generating simple statistics. Data persistence is file-based using Java serialization.

## 🚀 Features
- Add new books
- View all books and search by title/author
- Register members
- Borrow books with automatic 2-week due date
- Return books
- Overdue detection
- Library statistics (totals, borrowed, overdue)
- Persistent storage using files in the `data/` folder

## 🛠️ Tech & Requirements
- Java 17+ (source/target set to 17 in [pom.xml](pom.xml))
- Maven (optional, recommended for easy run)

## 📦 Project Structure
- [src/main/java/library/Main.java](src/main/java/library/Main.java) — Console entry point
- [src/main/java/library/Library.java](src/main/java/library/Library.java) — Core library logic
- [src/main/java/library/Book.java](src/main/java/library/Book.java) — Book model
- [src/main/java/library/Member.java](src/main/java/library/Member.java) — Member model
- [src/main/java/library/FileHandler.java](src/main/java/library/FileHandler.java) — File I/O for persistence
- [data/](data/) — Serialized data files: `books.txt`, `members.txt`
- [pom.xml](pom.xml) — Maven project file (exec plugin configured)

## ▶️ Build & Run

Using Maven (recommended):
```bash
mvn compile exec:java -Dexec.mainClass=library.Main
```

Manual compile & run (without Maven):
```bash
# from project root (Windows)
javac -d target/classes src/main/java/library/*.java
java -cp target/classes library.Main
```

Run inside VS Code
- Use the Java extension (Run on `Main`) or create a launch configuration that runs `library.Main`.

## 🗂 Data Files
The app persists objects to files under the `data/` directory. If the files don't exist they are created automatically. To reset data, remove `data/books.txt` and `data/members.txt`.

## 🧭 Usage (Console)
When running the app you'll see a menu:
```
=== LIBRARY MANAGEMENT SYSTEM ===
1. Add New Book
2. View All Books
3. Search Books
4. Register Member
5. Borrow Book
6. Return Book
7. View Library Statistics
8. Exit
```
Follow prompts to provide ISBN, title, author, year and member IDs. Borrowing sets a due date 2 weeks from the date of borrowing.

## 🔧 Notes & Troubleshooting
- The `pom.xml` includes the `exec-maven-plugin` configured for `library.Main` so `mvn exec:java` runs the app. If `mvn` is not available on your PATH, install Maven or use the manual `javac`/`java` commands above.
- If you encounter the "compact source file does not have main method" error when single-file running, run the compiled class instead (see commands above).

## ✅ Next Steps
- (Optional) I can add a VS Code `launch.json` entry to run `library.Main` directly from the editor. Want me to add it?

---
If you want any section shortened or expanded (examples, tests, or screenshots), tell me which part to change.
