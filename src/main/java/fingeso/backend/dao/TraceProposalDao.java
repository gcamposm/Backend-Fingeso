package fingeso.backend.dao;

import fingeso.backend.models.Proposal;
import fingeso.backend.models.TraceProposal;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TraceProposalDao extends MongoRepository<TraceProposal, ObjectId> {
    List<TraceProposal> findAllByProposal(Proposal proposal);
}
