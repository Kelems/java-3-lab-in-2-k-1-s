package org.example.service;

import org.example.model.Book;
import org.example.model.Reader;
import org.example.model.Rental;

import javax.jws.WebService;
import java.time.LocalDateTime;

//Для асинхронных и параллельных работ
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@WebService(endpointInterface = "org.example.service.IRentalService")
public class RentalService implements IRentalService {

    @Override
    public boolean rentBook(Reader reader, Book book, boolean isReadingRoomOnly) {
        System.out.println("Запуск сервиса Rent");

        // Проверка, что читатель может взять книгу
        if (!canRentBook(reader, book, isReadingRoomOnly)) {
            return false;
        }

        // Проверка, что книга есть
        if (book == null) {
            return false;
        }

        // Установка статуса книги как "выдана"
        book.setIssued(true);

        // Установка даты аренды
        LocalDateTime rentalDate = LocalDateTime.now();
        // Установка срока возврата
        LocalDateTime expectedReturnDate;
        if (isReadingRoomOnly) {
            //если в читальном зале
            expectedReturnDate = rentalDate.plusHours(3);
        } else {
            //если не в читальном зале
            expectedReturnDate = rentalDate.plusDays(7);
        }

        // Создание новой аренды и добавление ее в список аренд
        Rental rental = new Rental(Rental.getRentals().size() + 1, book.getId(), reader.getId(), rentalDate, null);
        rental.setExpectedReturnDate(expectedReturnDate);
        Rental.getRentals().add(rental);

        System.out.println("Сервис Rent выполнен");

        return true;
    }

    private boolean canRentBook(Reader reader, Book book, boolean isReadingRoomOnly) {
        // Создание экземпляров сервисов для проверки
        IReaderService readerService = new ReaderService();
        IAvailabilityService availabilityService = new AvailabilityService();

        try {
            // Запуск проверки читателя асинхронно
            CompletableFuture<Boolean> readerCheck = CompletableFuture.supplyAsync(() -> readerService.canBorrowBook(reader));

            // Запуск проверки доступности книги асинхронно
            CompletableFuture<Boolean> bookAvailabilityCheck = CompletableFuture.supplyAsync(() -> availabilityService.isBookAvailable(book));

            // Ожидание завершения обоих проверок
            boolean readerCanBorrow = readerCheck.get();
            boolean bookIsAvailable = bookAvailabilityCheck.get();

            // Проверка, что книга доступна только в читальном зале
            if (isReadingRoomOnly && !availabilityService.isBookForReadingRoomOnly(book)) {
                return false;
            }

            return readerCanBorrow && bookIsAvailable;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }
}