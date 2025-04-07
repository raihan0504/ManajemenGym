// Implementasi IBookService
import java.util.List;

public class BookServiceImpl implements IBookService {
    private IBookRepository bookRepository;
    
    // Constructor dependency injection
    public BookServiceImpl(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    @Override
    public boolean addBook(Book book) {
        return bookRepository.save(book);
    }
    
    @Override
    public boolean removeBook(String isbn) {
        return bookRepository.delete(isbn);
    }
    
    @Override
    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }
    
    @Override
    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findByTitle(title);
    }
    
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}