
// Class utama untuk menjalankan aplikasi
import java.util.List;
import java.util.Scanner;

public class LibraryManagementSystem {
    private IBookService bookService;
    private IUserService userService;
    private ILoanService loanService;
    private Scanner scanner;
    
    public LibraryManagementSystem() {
        // Initialize repositories
        IBookRepository bookRepository = new InMemoryBookRepository();
        IUserRepository userRepository = new InMemoryUserRepository();
        ILoanRepository loanRepository = new InMemoryLoanRepository();
        INotificationService notificationService = new EmailNotificationService();
        
        // Initialize services
        this.bookService = new BookServiceImpl(bookRepository);
        this.userService = new UserServiceImpl(userRepository);
        this.loanService = new LoanServiceImpl(loanRepository, bookRepository, userRepository, notificationService);
        
        this.scanner = new Scanner(System.in);
        
        // Add some sample data
        initializeSampleData();
    }
    
    private void initializeSampleData() {
        // Add sample books
        bookService.addBook(new Book("9780261102354", "The Fellowship of the Ring", "J.R.R. Tolkien", "Fantasy", 1954));
        bookService.addBook(new Book("9780261102361", "The Two Towers", "J.R.R. Tolkien", "Fantasy", 1954));
        bookService.addBook(new Book("9780261102378", "The Return of the King", "J.R.R. Tolkien", "Fantasy", 1955));
        bookService.addBook(new Book("9780060935467", "To Kill a Mockingbird", "Harper Lee", "Fiction", 1960));
        bookService.addBook(new Book("9780307474278", "The Da Vinci Code", "Dan Brown", "Thriller", 2003));
        
        // Add sample users
        userService.registerUser(new User(1, "John Doe", "john@example.com", "REGULAR"));
        userService.registerUser(new User(2, "Jane Smith", "jane@example.com", "PREMIUM"));
    }
    
    public void run() {
        boolean exit = false;
        
        while (!exit) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    handleBookManagement();
                    break;
                case 2:
                    handleUserManagement();
                    break;
                case 3:
                    handleLoanManagement();
                    break;
                case 4:
                    System.out.println("Terima kasih telah menggunakan Sistem Manajemen Perpustakaan!");
                    exit = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
        
        scanner.close();
    }
    
    private void displayMainMenu() {
        System.out.println("\n==== SISTEM MANAJEMEN PERPUSTAKAAN ====");
        System.out.println("1. Manajemen Buku");
        System.out.println("2. Manajemen Pengguna");
        System.out.println("3. Manajemen Peminjaman");
        System.out.println("4. Keluar");
        System.out.print("Pilih opsi (1-4): ");
    }
    
    private void handleBookManagement() {
        boolean back = false;
        
        while (!back) {
            System.out.println("\n==== MANAJEMEN BUKU ====");
            System.out.println("1. Tambah Buku Baru");
            System.out.println("2. Cari Buku berdasarkan ISBN");
            System.out.println("3. Cari Buku berdasarkan Judul");
            System.out.println("4. Lihat Semua Buku");
            System.out.println("5. Hapus Buku");
            System.out.println("6. Kembali ke Menu Utama");
            System.out.print("Pilih opsi (1-6): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    addNewBook();
                    break;
                case 2:
                    findBookByIsbn();
                    break;
                case 3:
                    searchBooksByTitle();
                    break;
                case 4:
                    displayAllBooks();
                    break;
                case 5:
                    removeBook();
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }
    
    private void addNewBook() {
        System.out.println("\n--- Tambah Buku Baru ---");
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Judul: ");
        String title = scanner.nextLine();
        System.out.print("Penulis: ");
        String author = scanner.nextLine();
        System.out.print("Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Tahun Terbit: ");
        int publishYear = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        Book book = new Book(isbn, title, author, genre, publishYear);
        boolean success = bookService.addBook(book);
        
        if (success) {
            System.out.println("Buku berhasil ditambahkan!");
        } else {
            System.out.println("Gagal menambahkan buku.");
        }
    }
    
    private void findBookByIsbn() {
        System.out.println("\n--- Cari Buku berdasarkan ISBN ---");
        System.out.print("Masukkan ISBN: ");
        String isbn = scanner.nextLine();
        
        Book book = bookService.findBookByIsbn(isbn);
        
        if (book != null) {
            displayBookDetails(book);
        } else {
            System.out.println("Buku dengan ISBN " + isbn + " tidak ditemukan.");
        }
    }
    
    private void searchBooksByTitle() {
        System.out.println("\n--- Cari Buku berdasarkan Judul ---");
        System.out.print("Masukkan judul (atau sebagian judul): ");
        String title = scanner.nextLine();
        
        List<Book> books = bookService.searchBooksByTitle(title);
        
        if (books.isEmpty()) {
            System.out.println("Tidak ada buku yang ditemukan dengan judul yang mengandung '" + title + "'.");
        } else {
            System.out.println("Daftar buku yang ditemukan (" + books.size() + " buku):");
            for (Book book : books) {
                System.out.println("------------------------------");
                displayBookDetails(book);
            }
        }
    }
    
    private void displayAllBooks() {
        System.out.println("\n--- Semua Buku ---");
        List<Book> books = bookService.getAllBooks();
        
        if (books.isEmpty()) {
            System.out.println("Tidak ada buku dalam perpustakaan.");
        } else {
            System.out.println("Jumlah buku: " + books.size());
            for (Book book : books) {
                System.out.println("------------------------------");
                displayBookDetails(book);
            }
        }
    }
    
    private void displayBookDetails(Book book) {
        System.out.println("ISBN: " + book.getIsbn());
        System.out.println("Judul: " + book.getTitle());
        System.out.println("Penulis: " + book.getAuthor());
        System.out.println("Genre: " + book.getGenre());
        System.out.println("Tahun Terbit: " + book.getPublishYear());
        System.out.println("Status: " + (book.isAvailable() ? "Tersedia" : "Dipinjam"));
    }
    
    private void removeBook() {
        System.out.println("\n--- Hapus Buku ---");
        System.out.print("Masukkan ISBN buku yang akan dihapus: ");
        String isbn = scanner.nextLine();
        
        boolean success = bookService.removeBook(isbn);
        
        if (success) {
            System.out.println("Buku berhasil dihapus!");
        } else {
            System.out.println("Gagal menghapus buku. ISBN mungkin tidak valid.");
        }
    }
    
    private void handleUserManagement() {
        boolean back = false;
        
        while (!back) {
            System.out.println("\n==== MANAJEMEN PENGGUNA ====");
            System.out.println("1. Daftarkan Pengguna Baru");
            System.out.println("2. Cari Pengguna berdasarkan ID");
            System.out.println("3. Lihat Semua Pengguna");
            System.out.println("4. Hapus Pengguna");
            System.out.println("5. Kembali ke Menu Utama");
            System.out.print("Pilih opsi (1-5): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    registerNewUser();
                    break;
                case 2:
                    findUserById();
                    break;
                case 3:
                    displayAllUsers();
                    break;
                case 4:
                    removeUser();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }
    
    private void registerNewUser() {
        System.out.println("\n--- Daftarkan Pengguna Baru ---");
        System.out.print("ID Pengguna: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Nama: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Tipe Keanggotaan (REGULAR/PREMIUM): ");
        String membershipType = scanner.nextLine().toUpperCase();
        
        User user = new User(userId, name, email, membershipType);
        boolean success = userService.registerUser(user);
        
        if (success) {
            System.out.println("Pengguna berhasil didaftarkan!");
        } else {
            System.out.println("Gagal mendaftarkan pengguna.");
        }
    }
    
    private void findUserById() {
        System.out.println("\n--- Cari Pengguna berdasarkan ID ---");
        System.out.print("Masukkan ID Pengguna: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        User user = userService.findUserById(userId);
        
        if (user != null) {
            displayUserDetails(user);
        } else {
            System.out.println("Pengguna dengan ID " + userId + " tidak ditemukan.");
        }
    }
    
    private void displayAllUsers() {
        System.out.println("\n--- Semua Pengguna ---");
        List<User> users = userService.getAllUsers();
        
        if (users.isEmpty()) {
            System.out.println("Tidak ada pengguna yang terdaftar.");
        } else {
            System.out.println("Jumlah pengguna: " + users.size());
            for (User user : users) {
                System.out.println("------------------------------");
                displayUserDetails(user);
            }
        }
    }
    
    private void displayUserDetails(User user) {
        System.out.println("ID: " + user.getUserId());
        System.out.println("Nama: " + user.getName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Tipe Keanggotaan: " + user.getMembershipType());
    }
    
    private void removeUser() {
        System.out.println("\n--- Hapus Pengguna ---");
        System.out.print("Masukkan ID pengguna yang akan dihapus: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        boolean success = userService.removeUser(userId);
        
        if (success) {
            System.out.println("Pengguna berhasil dihapus!");
        } else {
            System.out.println("Gagal menghapus pengguna. ID mungkin tidak