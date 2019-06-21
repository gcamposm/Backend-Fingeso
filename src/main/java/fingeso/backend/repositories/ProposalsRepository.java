package fingeso.backend.repositories;

import fingeso.backend.model.Proposals;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProposalsRepository extends MongoRepository<Proposals, String> {
    Proposals findBy_id(ObjectId _id);
}