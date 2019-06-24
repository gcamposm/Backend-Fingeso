package fingeso.backend.services;

import fingeso.backend.dao.UserDao;
import fingeso.backend.dto.UserDto;
import fingeso.backend.mappers.UserMapper;
import fingeso.backend.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

  @Autowired
  private UserDao userDao;

  @Autowired
  private UserMapper userMapper;

  /**
   *
   * @return
   */
  public List<UserDto> getAllUser(){

    List<User> users = userDao.findAll();
    return userMapper.mapToDtoList(users);
  }

  /**
   *
   * @param user
   * @return
   */
  public List<UserDto> createUser(User user){
    userDao.save(user);
    List<User> users = userDao.findAll();
    users.add(user);
    return userMapper.mapToDtoList(users);
  }

  /**
   *
   * @param firstName
   * @param lastName
   * @return
   */
  public List<UserDto> findUserByFullName(String firstName, String lastName){

    List<User> userFinded = userDao.findByFirstNameAndLastName(firstName, lastName);
    return userMapper.mapToDtoList(userFinded);
  }

  public List<UserDto> findUserById(int id){
    List<User> userFinded = userDao.findById(id);
    return userMapper.mapToDtoList(userFinded);
  }

  public void updateUserData(User user, int id){
    List<User> userFinded = userDao.findById(id);
    userFinded.get(0).setFirstName(user.getFirstName());
    userFinded.get(0).setLastName(user.getLastName());
    userFinded.get(0).setBirthDate(user.getBirthDate());
    userDao.save(userFinded.get(0));
  }

  public void deleteUser(int id){
    List<User> userFinded = userDao.findById(id);
    userDao.delete(userFinded.get(0));
  }
}
