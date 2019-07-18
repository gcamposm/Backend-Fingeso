package fingeso.backend.services;

import fingeso.backend.dao.TraceProposalDao;
import fingeso.backend.models.Proposal;
import fingeso.backend.models.TraceProposal;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class TraceProposalService {
    @Autowired
    private TraceProposalDao traceProposalDao;

    public TraceProposal createTrace(List<Integer> changes, Proposal proposal){
        Random random = new Random();
        List<TraceProposal> traceProposalList = traceProposalDao.findAll();
        TraceProposal traceProposal = traceProposalList.get(random.nextInt(traceProposalList.size() - 1));
        Date now = new Date();
        ObjectId id = ObjectId.get();
        traceProposal.set_id(id);
        traceProposal.setCommentary("");
        traceProposal.setTime(now);
        traceProposal.setUser(null);
        traceProposal.setProposal(proposal);
        traceProposal.setChanges(changes);
        return traceProposalDao.save(traceProposal);
    }
}
