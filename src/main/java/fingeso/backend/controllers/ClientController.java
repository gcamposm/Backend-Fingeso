package fingeso.backend.controllers;

import fingeso.backend.models.Client;
import fingeso.backend.repositories.ClientRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientRepository repository;

    @GetMapping("/")
    public List<Client> getAllClients() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable("id") ObjectId id) {
        return repository.findBy_id(id);
    }

    @PutMapping("/{id}")
    public void modifyClientById(@PathVariable("id") ObjectId id, @Valid @RequestBody Client client) {
        client.set_id(id);
        repository.save(client);
    }

    @PostMapping("/")
    public Client createClient(@Valid @RequestBody Client client) {
        client.set_id(ObjectId.get());
        repository.save(client);
        return client;
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable ObjectId id) {
        repository.delete(repository.findBy_id(id));
    }
}
