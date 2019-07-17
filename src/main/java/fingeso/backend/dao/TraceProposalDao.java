package fingeso.backend.dao;

import fingeso.backend.models.TraceProposal;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TraceProposalDao extends MongoRepository<TraceProposal, ObjectId> {
}
