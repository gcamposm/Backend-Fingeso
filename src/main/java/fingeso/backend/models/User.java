package fingeso.backend.models;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.List;

@Data
@Document(collection = "user")
public class User {

  @Id
  private ObjectId _id;
  private String firstName;
  private String lastName;
  private List<Proposal> proposals;
}


