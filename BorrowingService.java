import java.util.List;

// Interface provided untuk manajemen peminjaman
public interface BorrowingService {
    void borrowBook(String bookId, String memberId);
    void returnBook(String bookId, String memberId);
    List<Borrowing> getBorrowingsByMember(String memberId);
}
