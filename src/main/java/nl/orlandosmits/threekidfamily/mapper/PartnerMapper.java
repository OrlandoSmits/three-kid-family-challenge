package nl.orlandosmits.threekidfamily.mapper;

import java.util.Objects;
import nl.orlandosmits.threekidfamily.domain.Partner;
import nl.orlandosmits.threekidfamily.dto.request.PeopleRequestDto;
import nl.orlandosmits.threekidfamily.entity.PersonEntity;

import org.springframework.stereotype.Component;

@Component
public class PartnerMapper {

    public Partner mapFrom(PeopleRequestDto peopleRequestDto) {
        return Partner.builder()
                .id(Objects.nonNull(peopleRequestDto.partner()) ? peopleRequestDto.partner().id() : null)
                .build();
    }

    public Partner mapFrom(PersonEntity personEntity) {
        return Partner.builder()
                .id(personEntity.getPartnerId())
                .build();
    }

}
