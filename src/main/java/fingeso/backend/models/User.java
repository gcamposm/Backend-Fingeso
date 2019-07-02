package fingeso.backend.models;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.List;

@Data
@Document(collection = "users")
public class User {

  @Id
  private ObjectId _id;
  private String firstName;
  private String lastName;
  private List<Proposal> proposals;

  public void set_id(ObjectId _id) {
    this._id = _id;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setProposals(List<Proposal> proposals) {
    this.proposals = proposals;
  }

  public ObjectId get_id() {
    return _id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public List<Proposal> getProposals() {
    return proposals;
  }
}


