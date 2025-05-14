package nl.orlandosmits.threekidfamily.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Person {

    private Long id;
    private String name;
    private LocalDate birthDate;
    private Partner partner;
    private Parent parent1;
    private Parent parent2;
    private List<Child> children = new ArrayList<>();

    public boolean hasPartner() {
        return partner != null;
    }

    public boolean hasParent1() {
        return parent1 != null;
    }

    public boolean hasParent2() {
        return parent2 != null;
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }

    public List<Long> getChildrenIds() {
        return children.stream().map(Child::getId).collect(Collectors.toList());
    }

    public boolean hasExactlyThreeChildren() {
        if (this.children == null) {
            return false;
        }

        if (this.children.isEmpty()) {
            return false;
        }

        return children.size() == 3;
    }

    /**
     * A person with three children should have the same father.
     *
     * @return True if all three children have the same partner as mother or father..
     */
    public boolean allThreeChildrenHavePartnerAsMotherOrFather() {
        if (children.isEmpty()) {
            return false;
        }

        if (!hasPartner()) {
            return false;
        }

        return children.stream()
                .allMatch(child -> child.getParentIds().contains(partner.getId()));
    }

    public boolean oneChildIsUnderEighteen() {
        return this.children.stream()
                .anyMatch(Child::isUnderEighteen);
    }

}
