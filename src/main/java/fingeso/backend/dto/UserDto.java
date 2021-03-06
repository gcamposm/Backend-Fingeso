package fingeso.backend.dto;

import fingeso.backend.models.Proposal;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;

@Data
public class UserDto {
    private ObjectId _id;
    private String idStr;
    private String firstName;
    private String lastName;
    private String password;
    private Integer isAdmin;
    private List<Proposal> proposals;
}
