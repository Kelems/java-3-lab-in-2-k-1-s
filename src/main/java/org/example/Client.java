package org.example;

import org.example.client.*;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Client {
    public static void main(String[] args) throws Exception {
        // Создаем URL и QName для каждого сервиса
        URL readerServiceUrl = new URL("http://127.0.0.1:8081/reader?wsdl");
        URL availabilityServiceUrl = new URL("http://127.0.0.1:8081/availability?wsdl");
        URL rentalServiceUrl = new URL("http://127.0.0.1:8081/rental?wsdl");

        QName readerServiceQName = new QName("http://service.example.org/", "ReaderServiceService");
        QName availabilityServiceQName = new QName("http://service.example.org/", "AvailabilityServiceService");
        QName rentalServiceQName = new QName("http://service.example.org/", "RentalServiceService");

        // Создаем сервисы и получаем прокси
        IReaderService readerServiceProxy = Service.create(readerServiceUrl, readerServiceQName).getPort(IReaderService.class);
        IAvailabilityService availabilityServiceProxy = Service.create(availabilityServiceUrl, availabilityServiceQName).getPort(IAvailabilityService.class);
        IRentalService rentalServiceProxy = Service.create(rentalServiceUrl, rentalServiceQName).getPort(IRentalService.class);

        // Ввод данных пользователем
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID читателя: ");
        int readerId = scanner.nextInt();

        // Поиск читателя и книги по ID
        org.example.model.Reader modelReader = org.example.model.Reader.findReaderById(readerId);
        org.example.model.ReadingCard modelReadingCard = findReadingCardByReaderId(readerId);

        // Вывод данных читателя и его читательского билета
        System.out.println("Данные читателя:");
        System.out.println(modelReader);
        if (modelReadingCard != null) {
            System.out.println("Данные читательского билета:");
            System.out.println(modelReadingCard);
        } else {
            System.out.println("У читателя нет активного читательского билета.");
        }

        // Вывод книг, которые читатель арендует в данный момент
        List<org.example.model.Rental> currentRentals = findCurrentRentalsByReaderId(readerId);
        if (!currentRentals.isEmpty()) {
            System.out.println("Книги, которые читатель арендует в данный момент:");
            for (org.example.model.Rental rental : currentRentals) {
                org.example.model.Book book = org.example.model.Book.findBookById(rental.getBookId());
                System.out.println(book);
            }
        } else {
            System.out.println("Читатель не арендует книги в данный момент.");
        }

        System.out.print("Введите ID книги: ");
        int bookId = scanner.nextInt();

        org.example.model.Book modelBook = org.example.model.Book.findBookById(bookId);

        // Создаем объекты Reader и Book из сгенерированных классов
        org.example.client.Reader clientReader = new org.example.client.Reader();
        clientReader.setId(modelReader.getId());
        clientReader.setFirstName(modelReader.getFirstName());
        clientReader.setLastName(modelReader.getLastName());
        clientReader.setPhone(modelReader.getPhone());
        clientReader.setEmail(modelReader.getEmail());

        org.example.client.Book clientBook = new org.example.client.Book();
        clientBook.setId(modelBook.getId());
        clientBook.setTitle(modelBook.getTitle());
        clientBook.setAuthor(modelBook.getAuthor());
        clientBook.setLocation(modelBook.getLocation());
        clientBook.setIssued(modelBook.isIssued());
        clientBook.setReadingRoom(modelBook.isReadingRoom());

        // Асинхронные проверки
        CompletableFuture<Boolean> readerCheck = new CompletableFuture<>();
        CompletableFuture<Boolean> bookAvailabilityCheck = new CompletableFuture<>();
        CompletableFuture<Boolean> bookReadingRoomOnlyCheck = new CompletableFuture<>();

        // Проверка, может ли читатель взять книгу
        readerServiceProxy.canBorrowBookAsync(clientReader, new AsyncHandler<CanBorrowBookResponse>() {
            @Override
            public void handleResponse(Response<CanBorrowBookResponse> res) {
                try {
                    // Завершаем CompletableFuture с результатом проверки
                    readerCheck.complete(res.get().isReturn());
                } catch (Exception e) {
                    // В случае ошибки завершаем CompletableFuture с исключением
                    readerCheck.completeExceptionally(e);
                }
            }
        });

        // Проверка доступности книги
        availabilityServiceProxy.isBookAvailableAsync(clientBook, new AsyncHandler<IsBookAvailableResponse>() {
            @Override
            public void handleResponse(Response<IsBookAvailableResponse> res) {
                try {
                    // Завершаем CompletableFuture с результатом проверки
                    bookAvailabilityCheck.complete(res.get().isReturn());
                } catch (Exception e) {
                    // В случае ошибки завершаем CompletableFuture с исключением
                    bookAvailabilityCheck.completeExceptionally(e);
                }
            }
        });

        // Проверка, доступна ли книга только в читальном зале
        availabilityServiceProxy.isBookForReadingRoomOnlyAsync(clientBook, new AsyncHandler<IsBookForReadingRoomOnlyResponse>() {
            @Override
            public void handleResponse(Response<IsBookForReadingRoomOnlyResponse> res) {
                try {
                    // Завершаем CompletableFuture с результатом проверки
                    bookReadingRoomOnlyCheck.complete(res.get().isReturn());
                } catch (Exception e) {
                    // В случае ошибки завершаем CompletableFuture с исключением
                    bookReadingRoomOnlyCheck.completeExceptionally(e);
                }
            }
        });

        // Ожидание завершения всех проверок
        try {
            // Получаем результаты асинхронных проверок
            boolean canBorrow = readerCheck.get();
            boolean isBookAvailable = bookAvailabilityCheck.get();
            boolean isBookForReadingRoomOnly = bookReadingRoomOnlyCheck.get();

            // Проверка, может ли читатель взять книгу
            if (!canBorrow) {
                System.out.println("Читатель не может взять книгу.");
                return;
            }

            // Проверка доступности книги
            if (!isBookAvailable) {
                System.out.println("Книга '" + modelBook.getTitle() + "' в данный момент недоступна.");
                return;
            }

            // Запрос на аренду книги
            if (isBookForReadingRoomOnly) {
                System.out.println("Данная книга доступна в аренду только в читальном зале. Арендовать книгу на 3 часа? (да/нет)");
            } else {
                System.out.println("Книга " + modelBook.getTitle() + " найдена. Выдать книгу на неделю? (да/нет)");
            }

            // Получаем ответ пользователя
            String response = scanner.next();

            // Обрабатываем ответ пользователя
            if (response.toLowerCase().equals("да")) {
                boolean rentalResult = rentalServiceProxy.rentBook(clientReader, clientBook, isBookForReadingRoomOnly);
                if (rentalResult) {
                    System.out.println("Книга успешно арендована.");
                } else {
                    System.out.println("Ошибка при аренде книги.");
                }
            } else {
                System.out.println("Выдача книги отменена.");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    // Метод для поиска читательского билета по ID читателя
    private static org.example.model.ReadingCard findReadingCardByReaderId(int readerId) {
        for (org.example.model.ReadingCard card : org.example.model.ReadingCard.getReadingCards()) {
            if (card.getReaderId() == readerId && card.isActive()) {
                return card;
            }
        }
        return null;
    }

    // Метод для поиска книг, которые читатель арендует в данный момент
    private static List<org.example.model.Rental> findCurrentRentalsByReaderId(int readerId) {
        List<org.example.model.Rental> currentRentals = new ArrayList<>();
        for (org.example.model.Rental rental : org.example.model.Rental.getRentals()) {
            if (rental.getReaderId() == readerId && rental.getReturnDate() == null) {
                currentRentals.add(rental);
            }
        }
        return currentRentals;
    }
}