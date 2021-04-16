package mk.ukim.finki.emtlab2.service.impl;

import mk.ukim.finki.emtlab2.model.Author;
import mk.ukim.finki.emtlab2.model.Book;
import mk.ukim.finki.emtlab2.model.dto.BookDTO;
import mk.ukim.finki.emtlab2.model.exceptions.AuthorNotFound;
import mk.ukim.finki.emtlab2.model.exceptions.BookNotFound;
import mk.ukim.finki.emtlab2.repository.AuthorRepository;
import mk.ukim.finki.emtlab2.repository.BookRepository;
import mk.ukim.finki.emtlab2.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> save(BookDTO bookDto) {
        Author author=authorRepository
                .findById(bookDto.getAuthor()).orElseThrow(()->new AuthorNotFound(bookDto.getAuthor()));
        this.bookRepository.deleteByName(bookDto.getName());
        Book book=new Book(bookDto.getName(), bookDto.getCategory(),author,bookDto.getAvailableCopies());
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> edit(Long id, BookDTO bookDto) {
        Book oldBook=this.findById(id).orElseThrow(()->new BookNotFound(id));
        Author author=authorRepository
                .findById(bookDto.getAuthor()).orElseThrow(()->new AuthorNotFound(bookDto.getAuthor()));
        oldBook.setAuthor(author);
        oldBook.setCategory(bookDto.getCategory());
        oldBook.setName(bookDto.getName());
        oldBook.setAvailableCopies(bookDto.getAvailableCopies());
        return Optional.of(this.bookRepository.save(oldBook));
    }

    @Override
    public void deleteById(Long id) {

        this.bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> changeAvailableCopies(Long bookId) {
        Book old=this.findById(bookId).orElseThrow(()->new BookNotFound(bookId));
        old.setAvailableCopies(old.getAvailableCopies()-1);
        return Optional.of(this.bookRepository.save(old));
    }
}
