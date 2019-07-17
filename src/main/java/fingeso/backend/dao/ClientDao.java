package fingeso.backend.dao;

import fingeso.backend.models.Client;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientDao extends MongoRepository<Client, ObjectId> {
    Client findBy_id(ObjectId _id);
    Client findClientByIdStr(String id);
    Boolean existsBy_id(ObjectId _id);
}