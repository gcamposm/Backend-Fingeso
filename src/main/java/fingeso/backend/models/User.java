package fingeso.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user_nimbolu")
public class User {
  // ATTRIBUTES
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id")
  private Integer id;

  @NonNull
  @Column(name = "first_name")
  private String firstName;

  @NonNull
  @Column(name = "last_name")
  private String lastName;

  @NonNull
  @Column(name = "birth_date") @Temporal(TemporalType.DATE)
  private Date birthDate;

  // RELATIONS

  // user -> sale
  //@OneToMany(mappedBy = "userNimbolu", cascade = CascadeType.ALL)
  //@JsonIgnore
  //private List<Sale> saleList;
}

