package fingeso.backend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

  private Integer id;

  private String firstName;

  private String lastName;

  private Date birthDate;

}
