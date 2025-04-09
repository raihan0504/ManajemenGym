// Program utama
public class LibraryApp {
    public static void main(String[] args) {
        // Inisialisasi repositories
        BookRepository bookRepository = new InMemoryBookRepository();
        BorrowingRepository borrowingRepository = new InMemoryBorrowingRepository();
        
        // Inisialisasi services
        BookService bookService = new BookServiceImpl(bookRepository);
        BorrowingService borrowingService = new BorrowingServiceImpl(borrowingRepository, bookService);
        
        // Menambahkan buku ke perpustakaan
        bookService.addBook(new Book("B001", "Java Programming", "John Doe"));
        bookService.addBook(new Book("B002", "Design Patterns", "Gang of Four"));
        bookService.addBook(new Book("B003", "Clean Code", "Robert C. Martin"));
        
        // Membuat beberapa anggota
        Member member1 = new Member("M001", "Alice");
        Member member2 = new Member("M002", "Bob");
        
        // Simulasi peminjaman dan pengembalian buku
        System.out.println("\n--- Simulasi Perpustakaan ---");
        
        // Menampilkan semua buku
        System.out.println("\nDaftar buku tersedia:");
        bookService.findAllBooks().forEach(System.out::println);
        
        // Alice meminjam buku
        System.out.println("\nPeminjaman buku:");
        borrowingService.borrowBook("B001", member1.getId());
        
        // Bob mencoba meminjam buku yang sama
        borrowingService.borrowBook("B001", member2.getId());
        
        // Bob meminjam buku lain
        borrowingService.borrowBook("B002", member2.getId());
        
        // Menampilkan status buku
        System.out.println("\nStatus buku setelah peminjaman:");
        bookService.findAllBooks().forEach(System.out::println);
        
        // Alice mengembalikan buku
        System.out.println("\nPengembalian buku:");
        borrowingService.returnBook("B001", member1.getId());
        
        // Menampilkan status buku setelah pengembalian
        System.out.println("\nStatus buku setelah pengembalian:");
        bookService.findAllBooks().forEach(System.out::println);
        
        // Menampilkan riwayat peminjaman Alice
        System.out.println("\nRiwayat peminjaman " + member1.getName() + ":");
        borrowingService.getBorrowingsByMember(member1.getId()).forEach(System.out::println);
    }
}