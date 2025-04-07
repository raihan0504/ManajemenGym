// Implementasi IBookRepository
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryBookRepository implements IBookRepository {
    private Map<String, Book> books = new HashMap<>();
    
    @Override
    public boolean save(Book book) {
        books.put(book.getIsbn(), book);
        return true;
    }
    
    @Override
    public boolean delete(String isbn) {
        if (books.containsKey(isbn)) {
            books.remove(isbn);
            return true;
        }
        return false;
    }
    
    @Override
    public Book findByIsbn(String isbn) {
        return books.get(isbn);
    }
    
    @Override
    public List<Book> findByTitle(String title) {
        return books.values().stream()
            .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }
    
    @Override
    public boolean updateAvailability(String isbn, boolean isAvailable) {
        if (books.containsKey(isbn)) {
            books.get(isbn).setAvailable(isAvailable);
            return true;
        }
        return false;
    }
}