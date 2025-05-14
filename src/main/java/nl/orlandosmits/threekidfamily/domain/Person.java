package nl.orlandosmits.threekidfamily.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Person {

    private Long id;

    private String name;

    private Partner partner;

    private Parent parent1;
    private Parent parent2;

    public boolean hasPartner() {
        return partner != null;
    }
    
    public boolean hasParent1() {
        return parent1 != null;
    }
    
    public boolean hasParent2() {
        return parent2 != null;
    }
}
