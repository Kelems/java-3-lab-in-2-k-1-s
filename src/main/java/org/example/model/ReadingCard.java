package org.example.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReadingCard {
    private int id; // Номер читательского билета
    private int readerId; // ID читателя
    private Date issueDate; // Дата выдачи билета
    private Date expiryDate; // Дата истечения срока действия билета
    private boolean active; // Активен ли читательский билет

    // Статический список читательских билетов
    private static List<ReadingCard> readingCards = new ArrayList<>();

    static {
        readingCards.add(new ReadingCard(1, 1, new Date(), new Date(System.currentTimeMillis() + 31536000000L), true));
        readingCards.add(new ReadingCard(2, 2, new Date(), new Date(System.currentTimeMillis() + 31536000000L), true));
        readingCards.add(new ReadingCard(3, 3, new Date(), new Date(System.currentTimeMillis() + 31536000000L), true));
        readingCards.add(new ReadingCard(4, 4, new Date(), new Date(System.currentTimeMillis() + 31536000000L), true));
        readingCards.add(new ReadingCard(5, 6, new Date(), new Date(System.currentTimeMillis() + 31536000000L), true));
    }

    // Конструкторы
    public ReadingCard() {}

    public ReadingCard(int id, int readerId, Date issueDate, Date expiryDate, boolean active) {
        this.id = id;
        this.readerId = readerId;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
        this.active = active;
    }

    // Геттеры и сеттеры

    // Метод для получения списка читательских билетов
    public static List<ReadingCard> getReadingCards() {
        return readingCards;
    }
    public int getId() {
        return id;
    }
    public int getReaderId() {
        return readerId;
    }
    public Date getIssueDate() {
        return issueDate;
    }
    public Date getExpiryDate() {
        return expiryDate;
    }
    public boolean isActive() {
        return active;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return
            "|| Серийный номер: " + id +
            " || ID владельца: '" + readerId + '\'' +
            " || Дата выдачи билета: " + issueDate +
            " || Дата истечения срока действия билета: " + expiryDate  +
            " || Активен ли читательский билет: " + active
        ;
    }
}