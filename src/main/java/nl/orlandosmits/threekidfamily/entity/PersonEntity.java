package nl.orlandosmits.threekidfamily.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Setter
@Getter
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

    private Long partnerId;

    private Long parent1Id;

    private Long parent2Id;

}
