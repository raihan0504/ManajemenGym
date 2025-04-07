// Interface Required untuk repositori peminjaman
public interface ILoanRepository {
    boolean save(Loan loan);
    boolean update(Loan loan);
    Loan findByUserAndBook(int userId, String isbn);
    List<Loan> findByUserId(int userId);
    List<Loan> findOverdueLoans();
}