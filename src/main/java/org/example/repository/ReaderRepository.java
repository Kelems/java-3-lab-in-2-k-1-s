package org.example.repository;

import org.example.model.Reader;
import java.util.List;

// Интерфейс, отвечающий за взаимодействие с базой данных читателей.

public interface ReaderRepository {
    Reader findById(Long id);

    List<Reader> findAll();

    void save(Reader reader);

    void delete(Long id);
}