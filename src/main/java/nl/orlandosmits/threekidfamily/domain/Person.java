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

    public boolean hasPartner() {
        return partner != null;
    }
}
