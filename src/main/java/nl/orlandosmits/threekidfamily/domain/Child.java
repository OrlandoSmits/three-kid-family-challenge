package nl.orlandosmits.threekidfamily.domain;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Child {

    private Long id;

    private Long parent1Id;

    private Long parent2Id;

    private LocalDate birthDate;

    public Set<Long> getParentIds() {
        return Stream.of(parent1Id, parent2Id)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    public boolean isUnderEighteen() {
        return Period.between(birthDate, LocalDate.now()).getYears() < 18;
    }
}
