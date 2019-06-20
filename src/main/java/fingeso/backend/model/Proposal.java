package fingeso.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;

import javax.persistence.*;

public class Proposal {
    @Id
    public ObjectId _id;

    public String name;
    public String species;
    public String breed;

    // Constructors
    public Proposal() {}

    public Proposal(ObjectId _id, String name, String species, String breed) {
        this._id = _id;
        this.name = name;
        this.species = species;
        this.breed = breed;
    }

    // ObjectId needs to be converted to string
    public String get_id() { return _id.toHexString(); }
    public void set_id(ObjectId _id) { this._id = _id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }
}