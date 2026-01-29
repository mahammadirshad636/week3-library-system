package library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {

    private List<Book> books;
    private List<Member> members;
    private FileHandler fileHandler;

    public Library() {
        fileHandler = new FileHandler();
        books = new ArrayList<>(fileHandler.loadBooks());
        members = new ArrayList<>(fileHandler.loadMembers());

        System.out.println("Loaded " + books.size() + " books and "
                + members.size() + " members");
    }

    /* ===================== BOOK OPERATIONS ===================== */

    public void addBook(Book book) {
        books.add(book);
        fileHandler.saveBooks(books);
        System.out.println("Book added successfully!");
    }

    public void removeBook(String isbn) {
        Book book = findBookByIsbn(isbn);
        if (book != null) {
            books.remove(book);
            fileHandler.saveBooks(books);
            System.out.println("Book removed successfully!");
        } else {
            System.out.println("Book not found!");
        }
    }

    public Book findBookByIsbn(String isbn) {
        return books.stream()
                .filter(b -> b.getIsbn().equalsIgnoreCase(isbn))
                .findFirst()
                .orElse(null);
    }

    public List<Book> searchBooks(String keyword) {
        return books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(keyword.toLowerCase())
                        || b.getAuthor().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("\n=== ALL BOOKS ===");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    /* ===================== MEMBER OPERATIONS ===================== */

    public void registerMember(Member member) {
        members.add(member);
        fileHandler.saveMembers(members);
        System.out.println("Member registered successfully!");
    }

    public Member findMemberById(String id) {
        return members.stream()
                .filter(m -> m.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    /* ===================== BORROW BOOK ===================== */

    public void borrowBook(String isbn, String memberId) {
        Book book = findBookByIsbn(isbn);
        Member member = findMemberById(memberId);

        if (book == null || member == null) {
            System.out.println("Invalid book or member.");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("Book is already borrowed.");
            return;
        }

        book.setAvailable(false);
        book.setBorrowedBy(memberId);
        book.setDueDate(LocalDate.now().plusWeeks(2));

        member.borrowBook(isbn);

        fileHandler.saveBooks(books);
        fileHandler.saveMembers(members);

        System.out.println("Book borrowed successfully!");
        System.out.println("Due Date: " + book.getDueDate());
    }

    /* ===================== RETURN BOOK (YOUR FEATURE) ===================== */

    public void returnBook(String isbn, String memberId) {
        Book book = findBookByIsbn(isbn);
        Member member = findMemberById(memberId);

        if (book == null || member == null) {
            System.out.println("Invalid book or member.");
            return;
        }

        if (book.isAvailable()) {
            System.out.println("This book is not borrowed.");
            return;
        }

        book.setAvailable(true);
        book.setBorrowedBy(null);
        book.setDueDate(null);

        member.returnBook(isbn);

        fileHandler.saveBooks(books);
        fileHandler.saveMembers(members);

        System.out.println("Book returned successfully!");
    }

    /* ===================== STATISTICS ===================== */

    public void displayStatistics() {
        long totalBooks = books.size();
        long availableBooks = books.stream()
                .filter(Book::isAvailable)
                .count();
        long borrowedBooks = totalBooks - availableBooks;
        long overdueBooks = books.stream()
                .filter(b -> !b.isAvailable() && b.isOverdue())
                .count();

        System.out.println("\n=== LIBRARY STATISTICS ===");
        System.out.println("Total Books: " + totalBooks);
        System.out.println("Available Books: " + availableBooks);
        System.out.println("Borrowed Books: " + borrowedBooks);
        System.out.println("Overdue Books: " + overdueBooks);
        System.out.println("Registered Members: " + members.size());
    }
}
