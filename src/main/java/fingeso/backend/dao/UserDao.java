package fingeso.backend.dao;

import fingeso.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface UserDao extends JpaRepository<User, Long> {
  ArrayList<User> findAll();
  ArrayList<User> findByFirstNameAndLastName(String firsName, String lastName);
  ArrayList<User> findById(int id);
}
