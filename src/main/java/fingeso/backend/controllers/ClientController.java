package fingeso.backend.controllers;

import fingeso.backend.models.Client;
import fingeso.backend.dao.ClientDao;
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
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientDao clientDao;

    @GetMapping("/")
    public List<Client> getAllClients() {
        return clientDao.findAll();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable("id") ObjectId id) {
        return clientDao.findBy_id(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> updateClient(@RequestParam("clientId") String clientId, @RequestParam("name") String name, @RequestParam("company") String company, @RequestParam("score") Integer score) throws IOException
    {
        Client client = clientDao.findClientByIdStr(clientId);
        client.setName(name);
        client.setCompany(company);
        client.setScore(score);
        return ResponseEntity.ok(clientDao.save(client));
    }

    @PostMapping("/")
    public Client createClient(@Valid @RequestBody Client client) {
        client.set_id(ObjectId.get());
        clientDao.save(client);
        return client;
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable ObjectId id) {
        clientDao.delete(clientDao.findBy_id(id));
    }
}
