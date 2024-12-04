package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private int id;
    private String title; // Название книги
    private String author; // Автор / Авторы
    private String location; // Местоположение книги
    private boolean issued; // Арендуется ли книга?
    private boolean readingRoom; // Выдается ли только в читальном зале?

    // Статический блок инициализации
    private static List<Book> books = new ArrayList<>();

    static {
        books.add(new Book(1, "Война и мир", "Лев Толстой", "Стеллаж 1, Полка 2", false, false));
        books.add(new Book(2, "Преступление и наказание", "Федор Достоевский", "Стеллаж 2, Полка 3", false, false));
        books.add(new Book(3, "1984", "Джордж Оруэлл", "Стеллаж 3, Полка 4", true, false));
        books.add(new Book(4, "Убить пересмешника", "Харпер Ли", "Стеллаж 4, Полка 5", false, true));
        books.add(new Book(5, "Мастер и Маргарита", "Михаил Булгаков", "Стеллаж 5, Полка 6", false, false));
    }

    // Конструкторы
    public Book() {}

    public Book(int id, String title, String author, String location, boolean issued, boolean readingRoom) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.location = location;
        this.issued = issued;
        this.readingRoom = readingRoom;
    }

    // Геттеры и сеттеры

    public static List<Book> getBook() {
        return books;
    }

    public static Book findBookById(int bookId) {
        for (org.example.model.Book book : org.example.model.Book.getBooks()) {
            if (book.getId() == bookId) {
                return book;
            }
        }
        // Если readerId не найден, выбрасываем исключение
        System.out.println("Книга с ID " + bookId + " не найдена.");
        throw new IllegalArgumentException("Книга с ID " + bookId + " не найдена.");
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getLocation() {
        return location;
    }
    public boolean isIssued() {
        return issued;
    }
    public boolean isReadingRoom() {
        return readingRoom;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setIssued(boolean issued) {
        this.issued = issued;
    }
    public void setReadingRoom(boolean readingRoom) {
        this.readingRoom = readingRoom;
    }

    // Метод для получения списка книг
    public static List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return
                "|| Id: " + id +
                        " || Название: '" + title + '\'' +
                        " || Автор(ы): " + author +
                        " || Местоположение: " + location  +
                        " || Выдается: " + issued +
                        " || Только в читальном зале: " + readingRoom + " ||"
                ;
    }
}