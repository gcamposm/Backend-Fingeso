package fingeso.backend.models;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "clients")
public class Client {
    @Id
    private ObjectId _id;
    private String name;
    private String company;

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setProposals(List<Proposal> proposals) {
        this.proposals = proposals;
    }

    public ObjectId get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public List<Proposal> getProposals() {
        return proposals;
    }

    private List<Proposal> proposals;

    public Client addProposals (Client client, Proposal proposal){
        List<Proposal> proposals = client.getProposals();
        proposals.add(proposal);
        client.setProposals(proposals);
        return client;
    }
}
