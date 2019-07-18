package fingeso.backend.controllers;

import fingeso.backend.models.Proposal;
import fingeso.backend.dao.ProposalDao;
import fingeso.backend.models.TraceProposal;
import fingeso.backend.services.ProposalService;
import fingeso.backend.services.TraceProposalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/proposals")
public class ProposalController {
    @Autowired
    private ProposalDao proposalDao;

    @Autowired
    private ProposalService proposalService;

    @Autowired
    private TraceProposalService traceProposalService;

    @GetMapping("/")
    public List<Proposal> getAllProposals() {
        return proposalDao.findAll();
    }

    @GetMapping("/{id}")
    public Proposal getProposalById(@PathVariable("id") ObjectId id) {
        return proposalDao.findBy_id(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> updateProposal(@RequestParam("proposalId") String proposalId, @RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("files") List<String> files) throws IOException
    {
        Proposal proposal = proposalDao.findProposalByIdStr(proposalId);
        List<Integer> changes = new ArrayList<>();
        if(proposal.getName().equalsIgnoreCase(name))
        {
            changes.add(0);
        }
        else{
            changes.add(1);
            proposal.setName(name);
        }
        if(proposal.getDescription().equalsIgnoreCase(description))
        {
            changes.add(0);
        }
        else{
            changes.add(1);
            proposal.setDescription(description);
        }
        changes.add(0);
        changes.add(0);
        changes.add(0);
        if(proposal.getFiles().containsAll(files))
        {
            changes.add(0);
        }
        else {
            changes.add(1);
            if (proposal.getFiles().size() > files.size()) {
                for (String file : proposal.getFiles()
                ) {
                    if (!files.contains(file)) {
                        //ELIMINAR ARCHIVO
                    }
                }
            }
            proposal.setFiles(files);
        }
        traceProposalService.createTrace(changes, proposal);
        return ResponseEntity.ok(proposalDao.save(proposal));
    }

    @GetMapping("/create")
    public String createProposal() {
        Proposal proposal = proposalService.createProposal();
        return proposal.getIdStr();
    }

    @DeleteMapping("/{id}")
    public void deleteProposal(@PathVariable ObjectId id) {
        proposalDao.delete(proposalDao.findBy_id(id));
    }
}
