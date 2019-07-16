package fingeso.backend.dto;

import fingeso.backend.models.Proposal;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;

@Data
public class ClientDto {
    private ObjectId _id;
    private String name;
    private String company;
    private Integer score;
    private List<Proposal> proposals;
}
