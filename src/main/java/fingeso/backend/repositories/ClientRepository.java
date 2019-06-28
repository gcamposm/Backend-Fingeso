package fingeso.backend.repositories;

import fingeso.backend.models.Client;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, ObjectId> {
    Client findBy_id(ObjectId _id);
}