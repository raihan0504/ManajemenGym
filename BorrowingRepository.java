
import java.util.List;

// Interface required untuk penyimpanan peminjaman
interface BorrowingRepository {
    void save(Borrowing borrowing);
    Borrowing findByBookId(String bookId);
    List<Borrowing> findByMemberId(String memberId);
    void delete(String bookId, String memberId);
}