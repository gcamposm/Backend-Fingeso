package fingeso.backend.seeder;

import com.github.javafaker.Faker;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import fingeso.backend.models.Client;
import fingeso.backend.models.Proposal;
import fingeso.backend.models.User;
import fingeso.backend.dao.ClientDao;
import fingeso.backend.dao.ProposalDao;
import fingeso.backend.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("Duplicates")
@Component
public class DBSeeder implements CommandLineRunner {
    @Autowired
    private ProposalDao proposalDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ClientDao clientDao;

    public void seedUsers(){
        Faker faker = new Faker();
        for(int i = 0; i< 30; i++){
            User user = new User();
            List<Proposal> proposalList = new ArrayList<>();
            user.setFirstName(faker.name().firstName());
            user.setLastName(faker.name().lastName());
            user.setPassword("secret");
            user.setProposals(proposalList);
            userDao.save(user);
        }
    }

    public void seedClient(){
        Faker faker = new Faker();
        for(int i = 0; i< 30; i++){
            Client client = new Client();
            List<Proposal> proposalList = new ArrayList<>();
            client.setName(faker.name().name());
            client.setCompany(faker.company().name());
            client.setScore(5);
            client.setProposals(proposalList);
            clientDao.save(client);
            client.setIdStr(client.get_id().toHexString());
            clientDao.save(client);
        }
    }

    public void seedProposals() {
        Random random = new Random();
        Faker faker = new Faker();
        for (int i = 0; i < 30; i++) {
            Proposal proposal = new Proposal();
            List<User> userList = userDao.findAll();
            List<Client> clientList = clientDao.findAll();
            Client client = clientList.get(random.nextInt(clientList.size() - 1));
            User user = userList.get(random.nextInt(userList.size() - 1));
            proposal.setClientId(client.get_id());
            proposal.setClientIdStr(client.get_id().toHexString());
            proposal.setCreated(faker.date().birthday());
            proposal.setUserId(user.get_id());
            proposal.setDescription(faker.expression("descripcion"));
            proposal.setName(faker.name().firstName());
            proposal.setFiles(new ArrayList<>());
            proposal.setTeamSize(5);
            proposal.setBudget(100000);
            proposalDao.save(proposal);
            proposal.setIdStr(proposal.get_id().toHexString());
            proposalDao.save(proposal);
            List<Proposal> clientProposals = client.getProposals();
            List<Proposal> userProposals = user.getProposals();
            clientProposals.add(proposal);
            userProposals.add(proposal);
            client.setProposals(clientProposals);
            user.setProposals(userProposals);
            clientDao.save(client);
            userDao.save(user);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017));
        MongoDatabase db = mongoClient.getDatabase("Symbiose");
        MongoCollection proposalCollection = db.getCollection("proposals");
        MongoCollection clientCollection = db.getCollection("clients");
        MongoCollection userCollection = db.getCollection("users");
        clientCollection.drop();
        userCollection.drop();
        proposalCollection.drop();
        seedUsers();
        seedClient();
        seedProposals();
    }
}
