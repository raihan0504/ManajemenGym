import java.util.ArrayList;
import java.util.List;

// Implementasi BorrowingRepository
class InMemoryBorrowingRepository implements BorrowingRepository {
    private List<Borrowing> borrowings = new ArrayList<>();

    @Override
    public void save(Borrowing borrowing) {
        // Hapus jika sudah ada, lalu tambahkan yang baru
        borrowings.removeIf(b -> b.getBookId().equals(borrowing.getBookId()) && 
                           b.getMemberId().equals(borrowing.getMemberId()));
        borrowings.add(borrowing);
    }

    @Override
    public Borrowing findByBookId(String bookId) {
        return borrowings.stream()
                .filter(b -> b.getBookId().equals(bookId) && b.getReturnDate() == null)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Borrowing> findByMemberId(String memberId) {
        return borrowings.stream()
                .filter(b -> b.getMemberId().equals(memberId))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String bookId, String memberId) {
        borrowings.removeIf(b -> b.getBookId().equals(bookId) && 
                           b.getMemberId().equals(memberId));
    }
}