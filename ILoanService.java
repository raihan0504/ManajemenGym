// Interface Provided untuk layanan peminjaman
import java.util.List;

public interface ILoanService {
    boolean borrowBook(int userId, String isbn);
    boolean returnBook(int userId, String isbn);
    List<Book> getBorrowedBooks(int userId);
    boolean isBookAvailable(String isbn);
}