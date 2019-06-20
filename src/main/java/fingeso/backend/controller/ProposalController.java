package fingeso.backend.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import fingeso.backend.ProposalRepository;
import fingeso.backend.model.Proposal;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/proposal")
public class ProposalController {
    @Autowired
    private ProposalRepository repository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Proposal> getAllProposal() {
        return repository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Proposal getProposalById(@PathVariable("id") ObjectId id) {
        return repository.findBy_id(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void modifyProposalById(@PathVariable("id") ObjectId id, @Valid @RequestBody Proposal proposal) {
        proposal.set_id(id);
        repository.save(proposal);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Proposal createProposal(@Valid @RequestBody Proposal proposal) {
        proposal.set_id(ObjectId.get());
        repository.save(proposal);
        return proposal;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteProposal(@PathVariable ObjectId id) {
        repository.delete(repository.findBy_id(id));
    }
}
