package fingeso.backend.mappers;

import fingeso.backend.dto.UserDto;
import fingeso.backend.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

  public User mapToModel(UserDto userDto){

    User user = new User();
    user.setId(userDto.getId());
    user.setBirthDate(userDto.getBirthDate());
    user.setFirstName(userDto.getFirstName());
    user.setLastName(userDto.getLastName());

    return user;
  }

  public List<UserDto> mapToDtoList(List<User> user) {
    int i;

    ArrayList<UserDto> usersDto = new ArrayList<>();
    for(i=0;i<user.size();i++){
      usersDto.add(mapToDto(user.get(i)));
    }

    return usersDto;
  }

  public UserDto mapToDto (User user){

    UserDto userDto = new UserDto();
    userDto.setId(user.getId());
    userDto.setBirthDate(user.getBirthDate());
    userDto.setFirstName(user.getFirstName());
    userDto.setLastName(user.getLastName());

    return userDto;
  }
}
