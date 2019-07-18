package fingeso.backend.services;

import fingeso.backend.dao.UserDao;
import fingeso.backend.dto.UserDto;
import fingeso.backend.mappers.UserMapper;
import fingeso.backend.models.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserMapper userMapper;

    public List<UserDto> getAllUsers(){
        List<User> userList = userDao.findAll();
        return userMapper.mapToDtoArrayList(userList);
    }

    public UserDto getUserById(ObjectId id){
        if(userDao.existsBy_id(id)){
            return userMapper.mapToDto(userDao.findBy_id(id));
        }
        else{
            return  null;
        }
    }
    public void updateUser(UserDto userDto, ObjectId id){
        if(userDao.existsBy_id(id)){
            User user = userDao.findBy_id(id);
            user.setIdStr(userDto.getIdStr());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setPassword(userDto.getPassword());
            user.setProposals(userDto.getProposals());
            user.setIsAdmin(userDto.getIsAdmin());
            userDao.save(user);

        }

    }
    public void deleteUser(ObjectId id){
        User user = userDao.findBy_id(id);
        userDao.delete(user);

    }

    public UserDto createUser(UserDto userDto){
        return userMapper.mapToDto(userDao.save(userMapper.mapToModel(userDto)));
    }
}
