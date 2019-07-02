package fingeso.backend.models;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "proposals")
public class Proposal {
    @Id
    private ObjectId _id;
    private String name;
    private String description;
    private Date created;
    private Client client;
    private User creator;

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public ObjectId get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreated() {
        return created;
    }

    public Client getClient() {
        return client;
    }

    public User getCreator() {
        return creator;
    }
}
