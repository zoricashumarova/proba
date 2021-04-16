package mk.ukim.finki.emtlab2.service;

import mk.ukim.finki.emtlab2.model.Book;
import mk.ukim.finki.emtlab2.model.dto.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> findById(Long id);
    List<Book> findAll();
    Optional<Book> save(BookDTO bookDto);
    Optional<Book> edit(Long id, BookDTO bookDto);
    void deleteById(Long id);
    Optional<Book> changeAvailableCopies(Long bookId);

}