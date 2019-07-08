package fingeso.backend.controllers;

import fingeso.backend.models.Proposal;
import fingeso.backend.dao.ProposalDao;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/proposals")
public class ProposalController {
    @Autowired
    private ProposalDao repository;

    @GetMapping("/")
    public List<Proposal> getAllProposals() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Proposal getProposalById(@PathVariable("id") ObjectId id) {
        return repository.findBy_id(id);
    }

    @PutMapping("/{id}")
    public void modifyProposalById(@PathVariable("id") ObjectId id, @Valid @RequestBody Proposal proposal) {
        proposal.set_id(id);
        repository.save(proposal);
    }

    @PostMapping("/")
    public Proposal createProposal(@Valid @RequestBody Proposal proposal) {
        proposal.set_id(ObjectId.get());
        repository.save(proposal);
        return proposal;
    }

    @DeleteMapping("/{id}")
    public void deleteProposal(@PathVariable ObjectId id) {
        repository.delete(repository.findBy_id(id));
    }
}
