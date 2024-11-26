package org.example.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Rental {
    private int id;
    private int bookId; // ID книги
    private int readerId; // ID читателя
    private LocalDateTime rentalDate; // Дата взятия книги
    private LocalDateTime expectedReturnDate; // Ожидаемая дата возврата
    private LocalDateTime returnDate; // Дата возврата книги

    // Статический блок инициализации
    static {
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(1, 1, 1, LocalDateTime.now(), null));
        rentals.add(new Rental(2, 2, 2, LocalDateTime.now(), null));
        rentals.add(new Rental(3, 3, 3, LocalDateTime.now(), null));
        rentals.add(new Rental(4, 4, 4, LocalDateTime.now(), null));
        rentals.add(new Rental(5, 5, 5, LocalDateTime.now(), null));

        // Устанавливаем expectedReturnDate для каждой аренды
        for (Rental rental : rentals) {
            rental.setExpectedReturnDate(rental.calculateExpectedReturnDate(rental.getRentalDate(), rental.getBookId()));
        }
    }

    // Конструкторы
    public Rental() {}

    public Rental(int id, int bookId, int readerId, LocalDateTime rentalDate, LocalDateTime returnDate) {
        this.id = id;
        this.bookId = bookId;
        this.readerId = readerId;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.expectedReturnDate = calculateExpectedReturnDate(rentalDate, bookId);
    }

    // Метод для вычисления expectedReturnDate
    private LocalDateTime calculateExpectedReturnDate(LocalDateTime rentalDate, int bookId) {
        Book book = getBookById(bookId);
        if (book != null && book.isReadingRoom()) {
            return rentalDate.plusHours(3);
        } else {
            return rentalDate.plusDays(7);
        }
    }

    // Метод для получения объекта Book по bookId
    private Book getBookById(int bookId) {
        // Здесь должен быть код для получения объекта Book по bookId
        // Например, можно использовать список книг, который был инициализирован в статическом блоке класса Book
        List<Book> books = Book.getBooks(); // Предполагаем, что есть метод getBooks() в классе Book
        for (Book book : books) {
            if (book.getId() == bookId) {
                return book;
            }
        }
        return null;
    }

    // Геттеры
    public int getId() {
        return id;
    }

    public int getBookId() {
        return bookId;
    }

    public int getReaderId() {
        return readerId;
    }

    public LocalDateTime getRentalDate() {
        return rentalDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public LocalDateTime getExpectedReturnDate() {
        return expectedReturnDate;
    }

    // Сеттеры
    public void setId(int id) {
        this.id = id;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public void setRentalDate(LocalDateTime rentalDate) {
        this.rentalDate = rentalDate;
        this.expectedReturnDate = calculateExpectedReturnDate(rentalDate, this.bookId);
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public void setExpectedReturnDate(LocalDateTime expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    @Override
    public String toString() {
        return
                "|| Серийный номер: " + id +
                        " || ID книги: '" + bookId + '\'' +
                        " || ID читателя: " + readerId +
                        " || Дата взятия книги: " + rentalDate +
                        " || Ожидаемый срок возвращения: " + expectedReturnDate +
                        " || Дата возврата книги: " + returnDate
                ;
    }
}