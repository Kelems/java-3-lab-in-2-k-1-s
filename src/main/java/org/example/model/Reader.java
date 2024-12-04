package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Reader {
    private int id; // Уникальный идентификатор читателя
    private String firstName; // Имя читателя
    private String lastName; // Фамилия читателя
    private String phone; // Телефон читателя
    private String email; // email читателя

    // Статический блок инициализации
private static List<Reader> readers = new ArrayList<>();
    static {
        readers.add(new Reader(1, "Иван", "Иванов", "1234567890", "ivan@example.com"));
        readers.add(new Reader(2, "Петр", "Петров", "0987654321", "petr@example.com"));
        readers.add(new Reader(3, "Анна", "Сидорова", "1122334455", "anna@example.com"));
        readers.add(new Reader(4, "Елена", "Смирнова", "5566778899", "elena@example.com"));
        readers.add(new Reader(5, "Дмитрий", "Козлов", "9988776655", "dmitry@example.com"));
    }

    // Конструкторы
    public Reader() {}

    public Reader(int id, String firstName, String lastName, String phone, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    // Геттеры и сеттеры

    public static List<Reader> getReader() {
        return readers;
    }

    public static Reader findReaderById(int readerId) {
        for (org.example.model.Reader reader : org.example.model.Reader.getReader()) {
            if (reader.getId() == readerId) {
                return reader;
            }
        }
        System.out.println("Читатель с ID " + readerId + " не найден.");
        throw new IllegalArgumentException("Читатель с ID " + readerId + " не найден.");
    }
    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return
            " || Id: " + id +
            " || ФИО: '" + lastName + " " + firstName + '\'' +
            " || Номер телефона: " + phone  +
            " || Почтовый адрес: " + email
        ;
    }
}