
interface BookRepository {
    void save(Book book);
    Book findById(String id);
    List<Book> findAll();
    boolean delete(String id);
}

