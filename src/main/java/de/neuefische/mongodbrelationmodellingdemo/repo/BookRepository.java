package de.neuefische.mongodbrelationmodellingdemo.repo;

import de.neuefische.mongodbrelationmodellingdemo.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    Optional<Book> findByIsbn13(String isbn13);
}
