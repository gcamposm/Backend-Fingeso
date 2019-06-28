package fingeso.backend.controllers;

import fingeso.backend.models.User;
import fingeso.backend.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private UserRepository repository;

  @GetMapping("/")
  public List<User> getAllUsers() {
    return repository.findAll();
  }

  @GetMapping("/{id}")
  public User getUserById(@PathVariable("id") ObjectId id) {
    return repository.findBy_id(id);
  }

  @PutMapping("/{id}")
  public void modifyUserById(@PathVariable("id") ObjectId id, @Valid @RequestBody User user) {
    user.set_id(id);
    repository.save(user);
  }

  @PostMapping("/")
  public User createUser(@Valid @RequestBody User user) {
    user.set_id(ObjectId.get());
    repository.save(user);
    return user;
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable ObjectId id) {
    repository.delete(repository.findBy_id(id));
  }
}
