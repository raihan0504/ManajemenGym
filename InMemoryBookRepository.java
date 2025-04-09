// Implementasi BookRepository

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class InMemoryBookRepository implements BookRepository {
    private Map<String, Book> books = new HashMap<>();

    @Override
    public void save(Book book) {
        books.put(book.getId(), book);
    }

    @Override
    public Book findById(String id) {
        return books.get(id);
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }

    @Override
    public boolean delete(String id) {
        return books.remove(id) != null;
    }
}

