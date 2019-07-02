package fingeso.backend.seeder;

import com.github.javafaker.Faker;
import fingeso.backend.models.Client;
import fingeso.backend.models.Proposal;
import fingeso.backend.models.User;
import fingeso.backend.repositories.ClientRepository;
import fingeso.backend.repositories.ProposalRepository;
import fingeso.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@SuppressWarnings("Duplicates")
@Component
public class DBSeeder implements CommandLineRunner {
    @Autowired
    private ProposalRepository proposalRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClientRepository clientRepository;

    public void seedUsers(){
        Faker faker = new Faker();
        for(int i = 0; i< 30; i++){
            User user = new User();
            user.setFirstName(faker.name().firstName());
            user.setLastName(faker.name().lastName());
            userRepository.save(user);
        }
    }
    public void seedClient(){
        Faker faker = new Faker();
        for(int i = 0; i< 30; i++){
            Client client = new Client();
            client.setName(faker.name().name());
            client.setCompany(faker.company().name());
            clientRepository.save(client);
        }
    }

    public void seedProposals() {
        Random random = new Random();
        Faker faker = new Faker();
        for (int i = 0; i < 30; i++) {
            Proposal proposal = new Proposal();
            List<User> userList = userRepository.findAll();
            List<Client> clientList = clientRepository.findAll();
            Client client = clientList.get(random.nextInt(clientList.size() - 1));
            proposal.setClient(client);
            proposal.setCreated(faker.date().birthday());
            proposal.setCreator(userList.get(random.nextInt(userList.size() - 1)));
            proposal.setDescription(faker.expression(""));
            proposal.setName(faker.name().firstName());
            proposalRepository.save(proposal);
            //client.addProposals(client, proposal);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        seedUsers();
        seedClient();
        seedProposals();
    }
}
