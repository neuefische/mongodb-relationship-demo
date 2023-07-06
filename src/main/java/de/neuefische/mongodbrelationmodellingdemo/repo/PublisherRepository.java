package de.neuefische.mongodbrelationmodellingdemo.repo;

import de.neuefische.mongodbrelationmodellingdemo.model.Publisher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends MongoRepository<Publisher, String> {
}
