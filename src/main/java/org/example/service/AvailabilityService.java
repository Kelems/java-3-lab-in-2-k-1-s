package org.example.service;

import org.example.model.Book;

import javax.jws.WebService;

@WebService(endpointInterface = "org.example.service.IAvailabilityService")
public class AvailabilityService implements IAvailabilityService {

    @Override
    public boolean isBookAvailable(Book book) {
        // Проверка, что книга не выдана в аренду
        return !book.isIssued();
    }

    @Override
    public boolean isBookForReadingRoomOnly(Book book) {
        // Проверка, что книга доступна только в читальном зале
        return book.isReadingRoom();
    }
}