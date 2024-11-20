package org.example.model;

import java.util.Date;

// Класс, представляющий модель данных читателя.
public class Reader {
    private Long id; // Уникальный идентификатор читателя
    private String name; // Имя читателя
    private String cardNumber; // Номер читательского билета
    private int booksBorrowed; // Количество книг, которые читатель взял в библиотеке
    private Date lastBorrowDate; // Дата последнего взятия книги (если книга просрочена, это поле будет заполнено)

    // Конструкторы
    public Reader() {
    }

    public Reader(Long id, String name, String cardNumber, int booksBorrowed, Date lastBorrowDate) {
        this.id = id;
        this.name = name;
        this.cardNumber = cardNumber;
        this.booksBorrowed = booksBorrowed;
        this.lastBorrowDate = lastBorrowDate;
    }

    // Геттеры
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public int getBooksBorrowed() {
        return booksBorrowed;
    }
    public Date getLastBorrowDate() {
        return lastBorrowDate;
    }
    // Сеттеры
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public void setBooksBorrowed(int booksBorrowed) {
        this.booksBorrowed = booksBorrowed;
    }
    public void setLastBorrowDate(Date lastBorrowDate) {
        this.lastBorrowDate = lastBorrowDate;
    }
}