package org.example.service;

import org.example.model.Book;
import org.example.model.Rental;

import javax.jws.WebService;
import java.time.LocalDateTime;
import java.util.List;

@WebService(endpointInterface = "org.example.service.IAvailabilityService")
public class AvailabilityService implements IAvailabilityService {

    @Override
    public boolean isBookAvailable(Book book) {
        System.out.println("Проверка доступности книги: " + book.getTitle());
        // Проверка, что книга не выдана в аренду
        if (book.isIssued()) {
            // Если книга выдана, найдем срок ее возврата
            LocalDateTime returnDate = findReturnDateForBook(book.getId());
            if (returnDate != null) {
                System.out.println("Книга '" + book.getTitle() + "' уже выдана. Ожидаемая дата возврата: " + returnDate);
            } else {
                System.out.println("Книга '" + book.getTitle() + "' в данный момент недоступна");
            }
            return false;
        }
        System.out.println("Книга '" + book.getTitle() + "' доступна для аренды.");
        return true;
    }

    @Override
    public boolean isBookForReadingRoomOnly(Book book) {
        // Проверка, что книга доступна только в читальном зале
        return book.isReadingRoom();
    }

    private LocalDateTime findReturnDateForBook(int bookId) {
        List<Rental> rentals = Rental.getRentals();
        for (Rental rental : rentals) {
            if (rental.getBookId() == bookId && rental.getReturnDate() == null) {
                return rental.getExpectedReturnDate();
            }
        }
        return null;
    }
}