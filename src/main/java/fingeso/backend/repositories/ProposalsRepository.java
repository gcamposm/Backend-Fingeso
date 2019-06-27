package fingeso.backend.repositories;

import fingeso.backend.models.Proposals;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProposalsRepository extends MongoRepository<Proposals, ObjectId> {
    Proposals findBy_id(ObjectId _id);
}