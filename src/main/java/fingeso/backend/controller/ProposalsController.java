package fingeso.backend.controller;

import fingeso.backend.model.Proposals;
import fingeso.backend.repositories.ProposalsRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/proposals")
public class ProposalsController {
    @Autowired
    private ProposalsRepository repository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Proposals> getAllProposals() {
        return repository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Proposals getProposalById(@PathVariable("id") ObjectId id) {
        return repository.findBy_id(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void modifyProposalById(@PathVariable("id") ObjectId id, @Valid @RequestBody Proposals proposals) {
        proposals.set_id(id);
        repository.save(proposals);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Proposals createProposal(@Valid @RequestBody Proposals proposals) {
        proposals.set_id(ObjectId.get());
        repository.save(proposals);
        return proposals;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteProposal(@PathVariable ObjectId id) {
        repository.delete(repository.findBy_id(id));
    }
}
