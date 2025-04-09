import java.util.List;

// Interface required untuk penyimpanan buku
interface BookRepository {
    void save(Book book);
    Book findById(String id);
    List<Book> findAll();
    boolean delete(String id);
}