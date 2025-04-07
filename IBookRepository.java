// Interface Required untuk repositori buku

import java.util.List;

public interface IBookRepository {
    boolean save(Book book);
    boolean delete(String isbn);
    Book findByIsbn(String isbn);
    List<Book> findByTitle(String title);
    List<Book> findAll();
    boolean updateAvailability(String isbn, boolean isAvailable);
}