// Implementasi BorrowingService
class BorrowingServiceImpl implements BorrowingService {
    private BorrowingRepository borrowingRepository;
    private BookService bookService;

    public BorrowingServiceImpl(BorrowingRepository borrowingRepository, BookService bookService) {
        this.borrowingRepository = borrowingRepository;
        this.bookService = bookService;
    }

    @Override
    public void borrowBook(String bookId, String memberId) {
        Book book = bookService.findBookById(bookId);
        if (book == null) {
            System.out.println("Peminjaman gagal: Buku tidak ditemukan");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("Peminjaman gagal: Buku sedang dipinjam");
            return;
        }

        book.setAvailable(false);
        Borrowing borrowing = new Borrowing(bookId, memberId);
        borrowingRepository.save(borrowing);
        System.out.println("Buku " + book.getTitle() + " berhasil dipinjam oleh anggota dengan ID: " + memberId);
    }

    @Override
    public void returnBook(String bookId, String memberId) {
        Book book = bookService.findBookById(bookId);
        if (book == null) {
            System.out.println("Pengembalian gagal: Buku tidak ditemukan");
            return;
        }

        Borrowing borrowing = borrowingRepository.findByBookId(bookId);
        if (borrowing == null || !borrowing.getMemberId().equals(memberId)) {
            System.out.println("Pengembalian gagal: Data peminjaman tidak ditemukan");
            return;
        }

        book.setAvailable(true);
        borrowing.setReturnDate(LocalDate.now());
        borrowingRepository.save(borrowing);
        System.out.println("Buku " + book.getTitle() + " berhasil dikembalikan oleh anggota dengan ID: " + memberId);
    }

    @Override
    public List<Borrowing> getBorrowingsByMember(String memberId) {
        return borrowingRepository.findByMemberId(memberId);
    }
}
