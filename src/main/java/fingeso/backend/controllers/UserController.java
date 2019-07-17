package fingeso.backend.controllers;

import fingeso.backend.models.User;
import fingeso.backend.dao.UserDao;
import fingeso.backend.services.UserService;
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
@RequestMapping("/users")
public class UserController {
  @Autowired
  private UserDao userDao;

  @Autowired
  private UserService userService;

  @GetMapping("/")
  public List<User> getAllUsers() {
    return userDao.findAll();
  }

  @GetMapping("/{id}")
  public User getUserById(@PathVariable("id") ObjectId id) {
    return userDao.findBy_id(id);
  }

  @PutMapping("/{id}")
  public void modifyUserById(@PathVariable("id") ObjectId id, @Valid @RequestBody User user) {
    user.set_id(id);
    userDao.save(user);
  }

  @PostMapping("/")
  public User createUser(@Valid @RequestBody User user) {
    user.set_id(ObjectId.get());
    userDao.save(user);
    return user;
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable ObjectId id) {
    userDao.delete(userDao.findBy_id(id));
  }

  @RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<Object> login(@RequestParam("user") String name, @RequestParam("pass") String pass) throws IOException {
    List<User> users = userDao.findAll();
    for (User user : users
    ) {
      if (!user.getFirstName().equalsIgnoreCase(name)) {
        users.remove(user);
      }
      if (!user.getPassword().equalsIgnoreCase(pass)) {
        users.remove(user);
      }
    }
    if (users.size() == 1) {
      return ResponseEntity.ok(users.get(0));
    } else {
      return ResponseEntity.badRequest().build();
    }
  }
}