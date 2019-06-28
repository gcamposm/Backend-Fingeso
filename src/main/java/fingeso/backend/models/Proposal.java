package fingeso.backend.models;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "proposal")
public class Proposal {
    @Id
    private ObjectId _id;
    private String name;
    private String description;
    private Date created;
    private Client client;
    private User creator;
}
