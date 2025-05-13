package nl.orlandosmits.threekidfamily.dto.request;

import java.time.LocalDate;
import java.util.List;
import nl.orlandosmits.threekidfamily.dto.ChildDto;
import nl.orlandosmits.threekidfamily.dto.ParentDto;
import nl.orlandosmits.threekidfamily.dto.PartnerDto;

public record PeopleRequestDto(
        int id,
        String name,
        LocalDate birthDate,
        ParentDto parent1,
        ParentDto parent2,
        PartnerDto partner,
        List<ChildDto> children
) {

}
