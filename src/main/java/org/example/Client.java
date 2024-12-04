package org.example;

import org.example.client.*;
import org.example.model.Book;
import org.example.model.Reader;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Client {
    public static void main(String[] args) throws Exception {
        // Создаем URL для каждого сервиса
        URL readerServiceUrl = new URL("http://127.0.0.1:8081/reader?wsdl");
        URL availabilityServiceUrl = new URL("http://127.0.0.1:8081/availability?wsdl");
        URL rentalServiceUrl = new URL("http://127.0.0.1:8081/rental?wsdl");

        // Создаем QName для каждого сервиса
        QName readerServiceQName = new QName("http://service.example.org/", "ReaderServiceService");
        QName availabilityServiceQName = new QName("http://service.example.org/", "AvailabilityServiceService");
        QName rentalServiceQName = new QName("http://service.example.org/", "RentalServiceService");

        // Создаем сервисы
        Service readerService = Service.create(readerServiceUrl, readerServiceQName);
        Service availabilityService = Service.create(availabilityServiceUrl, availabilityServiceQName);
        Service rentalService = Service.create(rentalServiceUrl, rentalServiceQName);

        // Получаем прокси для каждого сервиса
        IReaderService readerServiceProxy = readerService.getPort(IReaderService.class);
        IAvailabilityService availabilityServiceProxy = availabilityService.getPort(IAvailabilityService.class);
        IRentalService rentalServiceProxy = rentalService.getPort(IRentalService.class);

        // Ввод данных пользователем
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ID читателя:");
        int readerId = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        System.out.println("Введите ID книги:");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        // Поиск читателя и книги по ID
        org.example.model.Reader modelReader = findReaderById(readerId);
        org.example.model.Book modelBook = findBookById(bookId);

        if (modelReader == null) {
            System.out.println("Читатель с ID " + readerId + " не найден.");
            return;
        }

        if (modelBook == null) {
            System.out.println("Книга с ID " + bookId + " не найдена.");
            return;
        }

        // Создаем объект Reader из сгенерированных классов
        org.example.client.Reader clientReader = new org.example.client.Reader();
        clientReader.setId(modelReader.getId());
        clientReader.setFirstName(modelReader.getFirstName());
        clientReader.setLastName(modelReader.getLastName());
        clientReader.setPhone(modelReader.getPhone());
        clientReader.setEmail(modelReader.getEmail());

        // Создаем объект Book из сгенерированных классов
        org.example.client.Book clientBook = new org.example.client.Book();
        clientBook.setId(modelBook.getId());
        clientBook.setTitle(modelBook.getTitle());
        clientBook.setAuthor(modelBook.getAuthor());
        clientBook.setLocation(modelBook.getLocation());
        clientBook.setIssued(modelBook.isIssued());
        clientBook.setReadingRoom(modelBook.isReadingRoom());

        // Асинхронная проверка, может ли читатель взять книгу
        CompletableFuture<Boolean> readerCheck = new CompletableFuture<>();
        readerServiceProxy.canBorrowBookAsync(clientReader, new AsyncHandler<CanBorrowBookResponse>() {
            @Override
            public void handleResponse(Response<CanBorrowBookResponse> res) {
                try {
                    readerCheck.complete(res.get().isReturn());
                } catch (Exception e) {
                    readerCheck.completeExceptionally(e);
                }
            }
        });

        // Асинхронная проверка доступности книги
        CompletableFuture<Boolean> bookAvailabilityCheck = new CompletableFuture<>();
        availabilityServiceProxy.isBookAvailableAsync(clientBook, new AsyncHandler<IsBookAvailableResponse>() {
            @Override
            public void handleResponse(Response<IsBookAvailableResponse> res) {
                try {
                    bookAvailabilityCheck.complete(res.get().isReturn());
                } catch (Exception e) {
                    bookAvailabilityCheck.completeExceptionally(e);
                }
            }
        });

        CompletableFuture<Boolean> bookReadingRoomOnlyCheck = new CompletableFuture<>();
        availabilityServiceProxy.isBookForReadingRoomOnlyAsync(clientBook, new AsyncHandler<IsBookForReadingRoomOnlyResponse>() {
            @Override
            public void handleResponse(Response<IsBookForReadingRoomOnlyResponse> res) {
                try {
                    bookReadingRoomOnlyCheck.complete(res.get().isReturn());
                } catch (Exception e) {
                    bookReadingRoomOnlyCheck.completeExceptionally(e);
                }
            }
        });

        // Ожидание завершения всех проверок
        try {
            boolean canBorrow = readerCheck.get();
            boolean isBookAvailable = bookAvailabilityCheck.get();
            boolean isBookForReadingRoomOnly = bookReadingRoomOnlyCheck.get();

            if (!canBorrow) {
                System.out.println("Читатель не может взять книгу.");
                return;
            }

            if (!isBookAvailable) {
                System.out.println("Книга недоступна.");
                return;
            }

            // Запрос на аренду книги
            if (isBookForReadingRoomOnly) {
                System.out.println("Данная книга доступна в аренду только в читальном зале. Арендовать книгу на 3 часа? (да/нет)");
            } else {
                System.out.println("Книга " + modelBook.getTitle() + " найдена. Выдать книгу на неделю? (да/нет)");
            }

            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("да")) {
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

    // Метод для поиска читателя по ID
    private static org.example.model.Reader findReaderById(int readerId) {
        for (org.example.model.Reader reader : org.example.model.Reader.getReader()) {
            if (reader.getId() == readerId) {
                return reader;
            }
        }
        return null;
    }

    // Метод для поиска книги по ID
    private static org.example.model.Book findBookById(int bookId) {
        for (org.example.model.Book book : org.example.model.Book.getBooks()) {
            if (book.getId() == bookId) {
                return book;
            }
        }
        return null;
    }
}