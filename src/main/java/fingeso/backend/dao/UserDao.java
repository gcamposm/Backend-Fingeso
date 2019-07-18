package fingeso.backend.dao;

import fingeso.backend.models.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserDao extends MongoRepository<User, ObjectId> {
    User findBy_id(ObjectId _id);
    User findUserByIdStr(String id);
    Boolean existsBy_id(ObjectId _id);
}