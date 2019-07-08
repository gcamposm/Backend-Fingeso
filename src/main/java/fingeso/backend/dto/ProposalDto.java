package fingeso.backend.dto;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;

@Data
public class ProposalDto {
    private ObjectId _id;
    private String name;
    private String description;
    private Date created;
    private ObjectId clientId;
    private ObjectId userId;
}
