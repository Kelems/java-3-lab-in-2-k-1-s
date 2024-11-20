package org.example.repository;

import org.example.model.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Реализация интерфейса ReaderRepository, использующая заглушку для хранения данных в памяти.
 */
public class ReaderRepositoryImpl implements ReaderRepository {
    private final Map<Long, Reader> readers = new HashMap<>();
    private Long nextId = 1L;

    @Override
    public Reader findById(Long id) {
        return readers.get(id);
    }

    @Override
    public List<Reader> findAll() {
        return new ArrayList<>(readers.values());
    }

    @Override
    public void save(Reader reader) {
        if (reader.getId() == null) {
            reader.setId(nextId++);
        }
        readers.put(reader.getId(), reader);
    }

    @Override
    public void delete(Long id) {
        readers.remove(id);
    }
}
