package fingeso.backend.repositories;

import fingeso.backend.models.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findBy_id(ObjectId _id);
}