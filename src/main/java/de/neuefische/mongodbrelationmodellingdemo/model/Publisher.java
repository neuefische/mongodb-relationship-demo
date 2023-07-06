package de.neuefische.mongodbrelationmodellingdemo.model;


import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document(collection = "publishers")
public record Publisher(
        @MongoId
        String publisherId,
        String name,
        String acronym,
        int foundationYear,
        @DBRef
        List<Book> books
) {
}
