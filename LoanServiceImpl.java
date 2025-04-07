// Implementasi ILoanService
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoanServiceImpl implements ILoanService {
    private ILoanRepository loanRepository;
    private IBookRepository bookRepository;
    private IUserRepository userRepository;
    private INotificationService notificationService;
    
    private static int nextLoanId = 1;
    
    // Constructor dependency injection
    public LoanServiceImpl(ILoanRepository loanRepository, IBookRepository bookRepository, 
                          IUserRepository userRepository, INotificationService notificationService) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }
    
    @Override
    public boolean borrowBook(int userId, String isbn) {
        // Check if user exists
        User user = userRepository.findById(userId);
        if (user == null) {
            return false;
        }
        
        // Check if book exists and is available
        Book book = bookRepository.findByIsbn(isbn);
        if (book == null || !book.isAvailable()) {
            return false;
        }
        
        // Create loan
        Loan loan = new Loan(nextLoanId++, userId, isbn, LocalDate.now());
        
        // Update book availability
        book.setAvailable(false);
        bookRepository.updateAvailability(isbn, false);
        
        // Save loan
        boolean success = loanRepository.save(loan);
        
        // Send notification
        if (success) {
            notificationService.sendBorrowNotification(user, book);
        }
        
        return success;
    }
    
    @Override
    public boolean returnBook(int userId, String isbn) {
        // Find the loan
        Loan loan = loanRepository.findByUserAndBook(userId, isbn);
        if (loan == null || loan.getStatus() == Loan.LoanStatus.RETURNED) {
            return false;
        }
        
        // Update loan
        loan.setReturnDate(LocalDate.now());
        
        // Update book availability
        Book book = bookRepository.findByIsbn(isbn);
        book.setAvailable(true);
        bookRepository.updateAvailability(isbn, true);
        
        // Update loan
        boolean success = loanRepository.update(loan);
        
        // Send notification
        if (success) {
            User user = userRepository.findById(userId);
            notificationService.sendReturnNotification(user, book);
        }
        
        return success;
    }
    
    @Override
    public List<Book> getBorrowedBooks(int userId) {
        List<Loan> userLoans = loanRepository.findByUserId(userId);
        List<Book> borrowedBooks = new ArrayList<>();
        
        for (Loan loan : userLoans) {
            if (loan.getStatus() == Loan.LoanStatus.ACTIVE || loan.getStatus() == Loan.LoanStatus.OVERDUE) {
                Book book = bookRepository.findByIsbn(loan.getIsbn());
                if (book != null) {
                    borrowedBooks.add(book);
                }
            }
        }
        
        return borrowedBooks;
    }
    
    @Override
    public boolean isBookAvailable(String isbn) {
        Book book = bookRepository.findByIsbn(isbn);
        return book != null && book.isAvailable();
    }
}