// Model kelas Borrowing
class Borrowing {
    private String bookId;
    private String memberId;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public Borrowing(String bookId, String memberId) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowDate = LocalDate.now();
        this.returnDate = null;
    }

    // Getter dan setter
    public String getBookId() { return bookId; }
    public String getMemberId() { return memberId; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    @Override
    public String toString() {
        return "Borrowing{bookId='" + bookId + "', memberId='" + memberId + 
               "', borrowDate=" + borrowDate + ", returnDate=" + returnDate + "}";
    }
}
