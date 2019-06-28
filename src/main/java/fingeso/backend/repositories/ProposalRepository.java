package fingeso.backend.repositories;

import fingeso.backend.models.Proposal;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProposalRepository extends MongoRepository<Proposal, ObjectId> {
    Proposal findBy_id(ObjectId _id);
}