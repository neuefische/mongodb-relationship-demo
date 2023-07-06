package de.neuefische.mongodbrelationmodellingdemo.controller;

import de.neuefische.mongodbrelationmodellingdemo.model.Book;
import de.neuefische.mongodbrelationmodellingdemo.repo.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{isbn13}")
    public Book getBookByIsbn13(@PathVariable String isbn13) {
        return bookRepository.findByIsbn13(isbn13).orElseThrow();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(new Book(
                null,
                book.isbn13(),
                book.title(),
                book.pages()
        ));
    }

    @PutMapping("/{isbn13}")
    public Book updateBook(@PathVariable String isbn13, @RequestBody Book book) {
        Book bookToUpdate = getBookByIsbn13(isbn13);
        return bookRepository.save(new Book(
                bookToUpdate.bookId(),
                bookToUpdate.isbn13(),
                book.title(),
                book.pages()
        ));
    }

    @DeleteMapping("/{isbn13}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable String isbn13) {
        Book book = getBookByIsbn13(isbn13);
        bookRepository.delete(book);
    }
}
