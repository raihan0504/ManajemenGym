// Interface Provided untuk layanan buku
public interface IBookService {
    boolean addBook(Book book);
    boolean removeBook(String isbn);
    Book findBookByIsbn(String isbn);
    List<Book> searchBooksByTitle(String title);
    List<Book> getAllBooks();