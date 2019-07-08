package fingeso.backend.dao;

import fingeso.backend.models.Proposal;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProposalDao extends MongoRepository<Proposal, ObjectId> {
    Proposal findBy_id(ObjectId _id);
}