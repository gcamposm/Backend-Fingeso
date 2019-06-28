package fingeso.backend.models;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "client")
public class Client {
    @Id
    private ObjectId _id;
    private String name;
    private String company;
    private List<Proposal> proposals;
}
