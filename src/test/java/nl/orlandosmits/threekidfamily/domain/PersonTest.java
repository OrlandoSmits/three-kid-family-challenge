package nl.orlandosmits.threekidfamily.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PersonTest {

	@Test
	public void hasPartner() {
		Person person = Person.builder()
				.partner(mock(Partner.class))
				.build();

		boolean result = person.hasPartner();

		assertThat(result).isTrue();
	}

	@Test
	public void hasPartner_noPartner() {
		Person person = Person.builder().build();

		boolean result = person.hasPartner();

		assertThat(result).isFalse();
	}
  
}