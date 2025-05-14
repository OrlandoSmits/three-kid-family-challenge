package nl.orlandosmits.threekidfamily.dto.request;

import java.time.LocalDate;
import java.util.List;
import nl.orlandosmits.threekidfamily.dto.ChildDto;
import nl.orlandosmits.threekidfamily.dto.ParentDto;
import nl.orlandosmits.threekidfamily.dto.PartnerDto;

public record PeopleRequestDto(
        long id,
        String name,
        LocalDate birthDate,
        ParentDto parent1,
        ParentDto parent2,
        PartnerDto partner,
        List<ChildDto> children
) {

    public boolean hasParent1() {
        return parent1.id() != null;
    }

    public boolean hasParent2() {
        return parent2.id() != null;
    }
}
