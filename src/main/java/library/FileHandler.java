package library;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private static final String BOOKS_FILE = "data/books.txt";
    private static final String MEMBERS_FILE = "data/members.txt";

    // ---------- BOOKS ----------
    public void saveBooks(List<Book> books) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(BOOKS_FILE))) {
            oos.writeObject(books);
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<Book> loadBooks() {
        File file = new File(BOOKS_FILE);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(file))) {
            return (List<Book>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Error loading books.");
            return new ArrayList<>();
        }
    }

    // ---------- MEMBERS ----------
    public void saveMembers(List<Member> members) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(MEMBERS_FILE))) {
            oos.writeObject(members);
        } catch (IOException e) {
            System.out.println("Error saving members: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<Member> loadMembers() {
        File file = new File(MEMBERS_FILE);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(file))) {
            return (List<Member>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Error loading members.");
            return new ArrayList<>();
        }
    }
}
