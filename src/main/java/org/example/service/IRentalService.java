package org.example.service;

import org.example.model.Book;
import org.example.model.Reader;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface IRentalService {
    @WebMethod
    boolean rentBook(Reader reader, Book book, boolean isReadingRoomOnly);
}