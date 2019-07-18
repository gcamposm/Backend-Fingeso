package fingeso.backend.controllers;

import fingeso.backend.dao.ProposalDao;
import fingeso.backend.dao.TraceProposalDao;
import fingeso.backend.models.TraceProposal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/traceproposals")
public class TraceProposalController {
    @Autowired
    private TraceProposalDao traceProposalDao;
    @Autowired
    private ProposalDao proposalDao;

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<List<TraceProposal>> updateProposal(@RequestParam("proposalId") String proposalId) throws IOException
    {
        /*List<TraceProposal> traceProposals = traceProposalDao.findAll();
        List<TraceProposal> aceptadas = traceProposals;
        aceptadas.clear();
        for (TraceProposal traceProposal:traceProposals) {
            if(traceProposal.getProposal().getIdStr().equalsIgnoreCase(proposalId))
            {
                aceptadas.add(traceProposal);
            }
        }*/
        return ResponseEntity.ok(traceProposalDao.findAllByProposal(proposalDao.findProposalByIdStr(proposalId)));
    }
}
