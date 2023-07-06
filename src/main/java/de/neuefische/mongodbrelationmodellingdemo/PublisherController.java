package de.neuefische.mongodbrelationmodellingdemo;

import de.neuefische.mongodbrelationmodellingdemo.model.Book;
import de.neuefische.mongodbrelationmodellingdemo.model.Publisher;
import de.neuefische.mongodbrelationmodellingdemo.repo.BookRepository;
import de.neuefische.mongodbrelationmodellingdemo.repo.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/publishers")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherRepository publisherRepository;
    private final BookRepository bookRepository;

    @GetMapping
    public List<Publisher> listPublishers() {
        return publisherRepository.findAll();
    }

    @GetMapping("/{publisherId}")
    public Publisher getPublisherById(@PathVariable String publisherId) {
        return publisherRepository.findById(publisherId).orElseThrow();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher addPublisher(@RequestBody Publisher publisher) {
        return publisherRepository.save(new Publisher(
                null,
                publisher.name(),
                publisher.acronym(),
                publisher.foundationYear(),
                List.of()
        ));
    }

    @PutMapping("/{publisherId}/books/{isbn13}")
    public Publisher addBookToPublisher(@PathVariable String publisherId, @PathVariable String isbn13) {
        Publisher publisher = getPublisherById(publisherId);
        Book book = bookRepository.findByIsbn13(isbn13).orElseThrow();
        List<Book> books = new ArrayList<>(publisher.books());
        books.add(book);
        return publisherRepository.save(new Publisher(
                publisher.publisherId(),
                publisher.name(),
                publisher.acronym(),
                publisher.foundationYear(),
                books
        ));
    }

    @PutMapping("/{publisherId}")
    public Publisher updatePublisher(@PathVariable String publisherId, @RequestBody Publisher publisher) {
        if (!publisherId.equals(publisher.publisherId())) {
            throw new IllegalArgumentException("PublisherId in path and body do not match");
        }
        return publisherRepository.save(publisher);
    }

}
