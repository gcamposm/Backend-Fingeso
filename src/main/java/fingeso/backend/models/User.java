package fingeso.backend.models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Document(collection = "users")
public class User {

  @Id
  public ObjectId _id;
  private String firstName;
  private String lastName;

  // Constructors
  public User() {}

  public User(ObjectId _id, String firstName, String lastName) {
    this._id = _id;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  // ObjectId needs to be converted to string
  public String get_id() { return _id.toHexString(); }
  public void set_id(ObjectId _id) { this._id = _id; }

  public String getFirstName() { return firstName; }
  public void setFirstName(String firstName) { this.firstName = firstName; }

  public String getLastName() { return lastName; }
  public void setLastName(String name) { this.lastName = lastName; }

}


