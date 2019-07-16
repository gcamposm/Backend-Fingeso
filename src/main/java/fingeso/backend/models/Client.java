package fingeso.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "clients")
public class Client {
    @Id
    private ObjectId _id;
    private String name;
    private String company;
    private Integer score;
    @JsonIgnore
    private List<Proposal> proposals;

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setProposals(List<Proposal> proposals) {this.proposals = proposals; }

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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Client addProposals(Proposal proposal){
        System.out.println("PROPOSAL: "+ proposal);
        List<Proposal> proposalList = this.getProposals();
        System.out.println("proposals: "+ proposalList);
        proposalList.add(proposal);
        System.out.println("proposals: "+ proposalList);
        this.setProposals(proposalList);
        return this;
    }
}
