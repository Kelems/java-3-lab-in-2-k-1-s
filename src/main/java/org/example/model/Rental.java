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

    private static List<Rental> rentals = new ArrayList<>();
    // Статический блок инициализации
    static {
        rentals.add(new Rental(1, 1, 1, LocalDateTime.now().minusDays(10), null)); // Просрочена
        rentals.add(new Rental(2, 2, 2, LocalDateTime.now().minusDays(5), null));
        rentals.add(new Rental(3, 3, 3, LocalDateTime.now().minusDays(3), null));
        rentals.add(new Rental(4, 4, 4, LocalDateTime.now().minusDays(2), null));
        rentals.add(new Rental(5, 5, 5, LocalDateTime.now().minusDays(1), null));
        rentals.add(new Rental(6, 6, 1, LocalDateTime.now().minusDays(6), null));
        rentals.add(new Rental(7, 7, 2, LocalDateTime.now().minusDays(7), null));
        rentals.add(new Rental(8, 8, 3, LocalDateTime.now().minusDays(8), null));
        rentals.add(new Rental(9, 9, 4, LocalDateTime.now().minusDays(9), null));
        rentals.add(new Rental(10, 10, 1, LocalDateTime.now().minusDays(10), null)); // Просрочена
        rentals.add(new Rental(11, 1, 1, LocalDateTime.now().minusDays(11), null)); // Просрочена
        rentals.add(new Rental(12, 2, 1, LocalDateTime.now().minusDays(12), null)); // Просрочена
        rentals.add(new Rental(13, 3, 3, LocalDateTime.now().minusDays(13), null)); // Просрочена
        rentals.add(new Rental(14, 4, 4, LocalDateTime.now().minusDays(14), null)); // Просрочена
        rentals.add(new Rental(15, 5, 5, LocalDateTime.now().minusDays(15), null)); // Просрочена

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
        List<Book> books = Book.getBooks();
        for (Book book : books) {
            if (book.getId() == bookId) {
                return book;
            }
        }
        return null;
    }

    // Геттеры

    // Метод для получения списка аренд
    public static List<Rental> getRentals() {
        return rentals;
    }

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