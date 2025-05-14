package nl.orlandosmits.threekidfamily.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Person")
public class PersonEntity {
    
    public PersonEntity(Long id) {
        this.id = id;
    }

    @Id
    private Long id;
    
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private PersonEntity partner;

    @ManyToOne
    private PersonEntity parent1;

    @ManyToOne
    private PersonEntity parent2;

}
