package org.example.service;

import org.example.model.Reader;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface IReaderService {
    @WebMethod
    boolean canBorrowBook(Reader reader);
}