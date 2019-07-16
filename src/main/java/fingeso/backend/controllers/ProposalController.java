package fingeso.backend.controllers;

import fingeso.backend.dto.ProposalDto;
import fingeso.backend.mappers.ProposalMapper;
import fingeso.backend.models.Proposal;
import fingeso.backend.dao.ProposalDao;
import fingeso.backend.services.ProposalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/proposals")
public class ProposalController {
    @Autowired
    private ProposalDao proposalDao;

    @Autowired
    private ProposalService proposalService;

    @GetMapping("/")
    public List<Proposal> getAllProposals() {
        return proposalDao.findAll();
    }

    @GetMapping("/{id}")
    public Proposal getProposalById(@PathVariable("id") ObjectId id) {
        return proposalDao.findBy_id(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> updateProposal(@RequestParam("proposalId") String proposalId, @RequestParam("name") String name, @RequestParam("description") String description) throws IOException
    {
        Proposal proposal = proposalDao.findProposalByIdStr(proposalId);
        proposal.setName(name);
        proposal.setDescription(description);
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
