package fingeso.backend;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import fingeso.backend.model.Proposal;

public interface ProposalRepository extends MongoRepository<Proposal, String> {
    Proposal findBy_id(ObjectId _id);
}