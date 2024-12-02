package org.example.service;

import org.example.model.Book;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface IAvailabilityService {
    @WebMethod
    boolean isBookAvailable(Book book);

    @WebMethod
    boolean isBookForReadingRoomOnly(Book book);
}