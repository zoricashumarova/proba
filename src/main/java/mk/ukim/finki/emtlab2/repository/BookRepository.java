package mk.ukim.finki.emtlab2.repository;


import mk.ukim.finki.emtlab2.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    void deleteByName(String name);
}
