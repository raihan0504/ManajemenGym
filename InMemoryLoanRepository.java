// Implementasi ILoanRepository
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryLoanRepository implements ILoanRepository {
    private Map<Integer, Loan> loans = new HashMap<>();
    
    @Override
    public boolean save(Loan loan) {
        loans.put(loan.getLoanId(), loan);
        return true;
    }
    
    @Override
    public boolean update(Loan loan) {
        if (loans.containsKey(loan.getLoanId())) {
            loans.put(loan.getLoanId(), loan);
            return true;
        }
        return false;
    }
    
    @Override
    public Loan findByUserAndBook(int userId, String isbn) {
        return loans.values().stream()
            .filter(loan -> loan.getUserId() == userId && loan.getIsbn().equals(isbn) && 
                   (loan.getStatus() == Loan.LoanStatus.ACTIVE || loan.getStatus() == Loan.LoanStatus.OVERDUE))
            .findFirst()
            .orElse(null);
    }
    
    @Override
    public List<Loan> findByUserId(int userId) {
        return loans.values().stream()
            .filter(loan -> loan.getUserId() == userId)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Loan> findOverdueLoans() {
        LocalDate today = LocalDate.now();
        return loans.values().stream()
            .filter(loan -> loan.getStatus() != Loan.LoanStatus.RETURNED && 
                   loan.getDueDate().isBefore(today))
            .collect(Collectors.toList());
    }
}