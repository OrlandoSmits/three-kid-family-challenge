package nl.orlandosmits.threekidfamily.service;

import org.springframework.stereotype.Component;

import nl.orlandosmits.threekidfamily.domain.Person;

@Component
public class PersonValidator {

	public boolean hasPartner(Person person) {
		if (person == null) {
			return false;
		}

		return person.hasPartner();
	}

	public boolean hasThreeChildrenWithSameMotherAndFather(Person person) {
		if (person == null) {
			return false;
		}

		// TODO: Implement
		return true;
	}

	public boolean hasOneChildUnderEighteen(Person person) {
		if (person == null) {
			return false;
		}

		// TODO: Implement
		return true;
	}

}
