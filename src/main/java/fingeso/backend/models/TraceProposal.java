package fingeso.backend.models;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "traceproposals")
public class TraceProposal {
    @Id
    private ObjectId _id;
    private String commentary;
    private Date time;
    private List<Integer> changes;
    private Proposal proposal;
    private User user;
}
