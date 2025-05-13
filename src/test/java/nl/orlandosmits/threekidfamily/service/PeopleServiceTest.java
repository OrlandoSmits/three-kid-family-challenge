package nl.orlandosmits.threekidfamily.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.orlandosmits.threekidfamily.domain.Partner;
import nl.orlandosmits.threekidfamily.domain.Person;
import nl.orlandosmits.threekidfamily.dto.request.PeopleRequestDto;
import nl.orlandosmits.threekidfamily.mapper.PersonMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PeopleServiceTest {

    @Mock
    private PersonMapper personMapper;

    @InjectMocks
    private PeopleService peopleService;

    @Test
    void getPerson() {
        PeopleRequestDto peopleRequestDto = mock(PeopleRequestDto.class);
        Person person = mock(Person.class);

        when(personMapper.mapFrom(peopleRequestDto)).thenReturn(person);

        Person result = peopleService.getPerson(peopleRequestDto);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(person);

        verify(personMapper, times(1)).mapFrom(peopleRequestDto);
    }

    @Test
    void isValidPerson() {
        Person person = new Person(1L, "John", new Partner());

        boolean result = peopleService.isValidPerson(person);

        assertThat(result).isTrue();
    }

    @Test
    void isValidPerson_hasNoPartner() {
        Person person = new Person(1L, "John", null);

        boolean result = peopleService.isValidPerson(person);

        assertThat(result).isFalse();
    }

}