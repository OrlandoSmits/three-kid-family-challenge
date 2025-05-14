package nl.orlandosmits.threekidfamily.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import nl.orlandosmits.threekidfamily.domain.Person;

@ExtendWith(MockitoExtension.class)
class PersonValidatorTest {

	@InjectMocks
	private PersonValidator personValidator;

	@Test
	public void hasPartner() {
		Person person = mock(Person.class);

		when(person.hasPartner()).thenReturn(true);

		boolean result = personValidator.hasPartner(person);

		assertThat(result).isTrue();
	}

	@Test
	public void hasPartner_noPartner() {
		Person person = mock(Person.class);

		when(person.hasPartner()).thenReturn(false);

		boolean result = personValidator.hasPartner(person);

		assertThat(result).isFalse();
	}
}