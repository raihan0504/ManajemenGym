// Model kelas Member
class Member {
    private String id;
    private String name;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter
    public String getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Member{id='" + id + "', name='" + name + "'}";
    }
}

// Implementasi BookRepository
class InMemoryBookRepository implements BookRepository {
    private Map<String, Book> books = new HashMap<>();

    @Override
    public void save(Book book) {
        books.put(book.getId(), book);
    }

    @Override
    public Book findById(String id) {
        return books.get(id);
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }

    @Override
    public boolean delete(String id) {
        return books.remove(id) != null;
    }
}

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
