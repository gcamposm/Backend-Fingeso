package fingeso.backend.models;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "proposals")
public class TraceProposal {
    @Id
    private ObjectId _id;
    private String commentary;
    private Date time;
    private String changes;
    private Proposal proposal;
}
