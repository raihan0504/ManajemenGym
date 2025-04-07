
// Class untuk entitas Peminjaman
import java.time.LocalDate;

public class Loan {
    private int loanId;
    private int userId;
    private String isbn;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private LoanStatus status;
    
    public enum LoanStatus {
        ACTIVE,
        RETURNED,
        OVERDUE
    }
    
    public Loan(int loanId, int userId, String isbn, LocalDate borrowDate) {
        this.loanId = loanId;
        this.userId = userId;
        this.isbn = isbn;
        this.borrowDate = borrowDate;
        this.dueDate = borrowDate.plusDays(14); // 2 weeks loan period
        this.status = LoanStatus.ACTIVE;
    }
    
    // Getters and Setters
    public int getLoanId() {
        return loanId;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public LocalDate getBorrowDate() {
        return borrowDate;
    }
    
    public LocalDate getDueDate() {
        return dueDate;
    }
    
    public LocalDate getReturnDate() {
        return returnDate;
    }
    
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
        this.status = LoanStatus.RETURNED;
    }
    
    public LoanStatus getStatus() {
        return status;
    }
    
    public void setStatus(LoanStatus status) {
        this.status = status;
    }
    
    public boolean isOverdue() {
        if (returnDate == null && LocalDate.now().isAfter(dueDate)) {
            this.status = LoanStatus.OVERDUE;
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", userId=" + userId +
                ", isbn='" + isbn + '\'' +
                ", borrowDate=" + borrowDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                ", status=" + status +
                '}';
    }
}

