package org.example.service;

import org.example.model.Book;

import javax.jws.WebService;

@WebService(endpointInterface = "org.example.service.IAvailabilityService")
public class AvailabilityService implements IAvailabilityService {

    @Override
    public boolean isBookAvailable(Book book) {
        // Проверка, что книга есть и не выдана в аренду
        if (book == null) return false;
        return !book.isIssued();
    }

    @Override
    public boolean isBookForReadingRoomOnly(Book book) {
        // Проверка, что книга не есть и доступна только в читальном зале
        if (book == null) return false;
        return book.isReadingRoom();
    }
}