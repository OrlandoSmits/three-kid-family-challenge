package nl.orlandosmits.threekidfamily.mapper;

import java.util.Optional;
import nl.orlandosmits.threekidfamily.domain.Parent;
import nl.orlandosmits.threekidfamily.dto.request.PeopleRequestDto;
import nl.orlandosmits.threekidfamily.entity.PersonEntity;
import org.springframework.stereotype.Component;

@Component
public class ParentMapper {

    public Optional<Parent> mapParent1From(PeopleRequestDto peopleRequestDto) {
        if (!peopleRequestDto.hasParent1()) {
            return Optional.empty();
        }

        return Optional.of(Parent.builder()
                .id(peopleRequestDto.parent1().id())
                .build());
    }

    public Optional<Parent> mapParent2From(PeopleRequestDto peopleRequestDto) {
        if (!peopleRequestDto.hasParent2()) {
            return Optional.empty();
        }

        return Optional.of(Parent.builder()
                .id(peopleRequestDto.parent2().id())
                .build());
    }

    public Parent mapParent1From(PersonEntity personEntity) {
        if (personEntity.getParent1Id() == null) {
            return null;
        }

        return Parent.builder()
                .id(personEntity.getPartnerId())
                .build();
    }

    public Parent mapParent2From(PersonEntity personEntity) {
        if (personEntity.getParent2Id() == null) {
            return null;
        }

        return Parent.builder()
                .id(personEntity.getPartnerId())
                .build();
    }

}
