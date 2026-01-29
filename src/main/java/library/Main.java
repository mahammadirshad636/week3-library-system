package library;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Library library = new Library();
        int choice;

        do {
            showMenu();
            choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1 -> addBook(library);
                case 2 -> library.displayAllBooks();
                case 3 -> searchBooks(library);
                case 4 -> registerMember(library);
                case 5 -> borrowBook(library);
                case 6 -> returnBook(library);
                case 7 -> library.displayStatistics();
                case 8 -> System.out.println("Exiting system. Goodbye!");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 8);
    }

    private static void showMenu() {
        System.out.println("\n=== LIBRARY MANAGEMENT SYSTEM ===");
        System.out.println("1. Add New Book");
        System.out.println("2. View All Books");
        System.out.println("3. Search Books");
        System.out.println("4. Register Member");
        System.out.println("5. Borrow Book");
        System.out.println("6. Return Book");
        System.out.println("7. View Library Statistics");
        System.out.println("8. Exit");
    }

    private static void addBook(Library library) {
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        int year = getIntInput("Year: ");

        library.addBook(new Book(isbn, title, author, year));
    }

    private static void searchBooks(Library library) {
        System.out.print("Enter keyword: ");
        library.searchBooks(scanner.nextLine())
                .forEach(System.out::println);
    }

    private static void registerMember(Library library) {
        System.out.print("Member ID: ");
        String id = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();

        library.registerMember(new Member(id, name));
    }

    private static void borrowBook(Library library) {
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Member ID: ");
        String memberId = scanner.nextLine();

        library.borrowBook(isbn, memberId);
    }

    private static void returnBook(Library library) {
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Member ID: ");
        String memberId = scanner.nextLine();

        library.returnBook(isbn, memberId);
    }

    private static int getIntInput(String msg) {
        System.out.print(msg);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Try again: ");
            scanner.next();
        }
        int val = scanner.nextInt();
        scanner.nextLine();
        return val;
    }
}
