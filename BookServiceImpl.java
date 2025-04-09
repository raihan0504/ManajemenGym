
import java.util.List;

// Implementasi BookService
class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
        System.out.println("Buku ditambahkan: " + book.getTitle());
    }

    @Override
    public Book findBookById(String id) {
        Book book = bookRepository.findById(id);
        if (book == null) {
            System.out.println("Buku dengan ID: " + id + " tidak ditemukan");
        }
        return book;
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public boolean removeBook(String id) {
        boolean result = bookRepository.delete(id);
        if (result) {
            System.out.println("Buku dengan ID: " + id + " berhasil dihapus");
        } else {
            System.out.println("Buku dengan ID: " + id + " tidak ditemukan");
        }
        return result;
    }
}
