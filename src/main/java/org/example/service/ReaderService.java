package org.example.service;

import org.example.model.Reader;
import org.example.model.ReadingCard;
import org.example.model.Rental;

import javax.jws.WebService;
import java.time.LocalDateTime;
import java.util.List;

@WebService(endpointInterface = "org.example.service.IReaderService")
public class ReaderService implements IReaderService {

    @Override
    public boolean canBorrowBook(Reader reader) {
        // Проверка, что читатель есть
        if (reader == null) return false;

        // Проверка есть ли у читателя билет
        if (!hasValidReadingCard(reader.getId())) return false;

        // Проверка, что читателем взято < 4 книг
        if (hasTooManyBooks(reader.getId())) return false;

        // Проверка, что у читателя нет просроченных книг
        if (hasOverdueBooks(reader.getId())) return false;

        return true;
    }

    private boolean hasValidReadingCard(int readerId) {
        // Получение списка читательских билетов
        List<ReadingCard> cards = ReadingCard.getReadingCards();
        for (ReadingCard card : cards) {
            // Проверка, что билет активен и принадлежит данному читателю
            if (card.getReaderId() == readerId && card.isActive()) {
                return true;
            }
        }
        return false;
    }

    private boolean hasTooManyBooks(int readerId) {
        // Получение списка аренд
        List<Rental> rentals = Rental.getRentals();
        int count = 0;
        for (Rental rental : rentals) {
            // Подсчет количества взятых книг, которые еще не возвращены
            if (rental.getReaderId() == readerId && rental.getReturnDate() == null) {
                count++;
            }
        }
        return count >= 4;
    }

    private boolean hasOverdueBooks(int readerId) {
        // Получение списка аренд
        List<Rental> rentals = Rental.getRentals();
        for (Rental rental : rentals) {
            // Проверка, что у читателя есть просроченные книги
            if (rental.getReaderId() == readerId && rental.getReturnDate() == null && rental.getExpectedReturnDate().isBefore(LocalDateTime.now())) {
                return true;
            }
        }
        return false;
    }
}