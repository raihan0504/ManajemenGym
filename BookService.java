import java.util.List;

// Interface provided untuk manajemen buku
public interface BookService {
    void addBook(Book book);
    Book findBookById(String id);
    List<Book> findAllBooks();
    boolean removeBook(String id);
}
