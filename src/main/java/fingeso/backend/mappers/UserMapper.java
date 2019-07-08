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
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getFirstName());
        user.setProposals(userDto.getProposals());
        return user;
    }

    public List<UserDto> mapToDtoArrayList(List<User> userArrayList) {
        int i;

        List<UserDto> userDtoArrayList = new ArrayList<>();
        for(i=0;i<userArrayList.size();i++){
            userDtoArrayList.add(mapToDto(userArrayList.get(i)));
        }

        return userDtoArrayList;
    }

    public UserDto mapToDto (User user){

        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getFirstName());
        userDto.setProposals(user.getProposals());
        return userDto;
    }
}
