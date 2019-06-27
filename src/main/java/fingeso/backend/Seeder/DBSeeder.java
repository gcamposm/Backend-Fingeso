package fingeso.backend.Seeder;

import com.github.javafaker.Faker;
import fingeso.backend.models.User;
import fingeso.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBSeeder implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;


    public void seedUsers(){
        Faker faker = new Faker();
        for(int i = 0; i< 30; i++){
            User user = new User();
            user.setFirstName(faker.name().firstName());
            user.setLastName(faker.name().lastName());
            userRepository.save(user);
        }

    }

    @Override
    public void run(String... args) throws Exception {
        seedUsers();
    }
}
