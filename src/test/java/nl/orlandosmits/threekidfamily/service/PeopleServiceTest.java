package nl.orlandosmits.threekidfamily.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import nl.orlandosmits.threekidfamily.domain.Person;
import nl.orlandosmits.threekidfamily.dto.request.PeopleRequestDto;
import nl.orlandosmits.threekidfamily.entity.PersonEntity;
import nl.orlandosmits.threekidfamily.mapper.PersonMapper;
import nl.orlandosmits.threekidfamily.repository.PersonRepository;

@ExtendWith(MockitoExtension.class)
class PeopleServiceTest {

    @Mock
    private PersonMapper personMapper;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonValidator personValidator;

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
    void anyPersonIsValid() {
        PersonEntity personEntity = mock(PersonEntity.class);

        List<PersonEntity> personEntities = List.of(personEntity);

        Person person = mock(Person.class);

        when(personRepository.findAll()).thenReturn(personEntities);
        when(personMapper.mapFrom(any(PersonEntity.class)))
                .thenReturn(person);

        when(personValidator.hasPartner(person)).thenReturn(true);
        when(personValidator.hasThreeChildrenWithPartnerAsParent(person)).thenReturn(true);
        when(personValidator.hasOneChildUnderEighteen(person)).thenReturn(true);

        boolean result = peopleService.anyPersonIsValid();

        assertThat(result).isTrue();

        verify(personRepository, times(1)).findAll();

        verify(personMapper, atLeastOnce()).mapFrom(personEntity);
        verify(personValidator, times(1)).hasPartner(person);
        verify(personValidator, times(1)).hasThreeChildrenWithPartnerAsParent(person);
        verify(personValidator, times(1)).hasOneChildUnderEighteen(person);
    }

    @Test
    void anyPersonIsValid_hasNoPartner() {
        PersonEntity personEntity = mock(PersonEntity.class);

        List<PersonEntity> personEntities = List.of(personEntity);

        Person person = mock(Person.class);

        when(personRepository.findAll()).thenReturn(personEntities);
        when(personMapper.mapFrom(any(PersonEntity.class)))
                .thenReturn(person);

        when(personValidator.hasPartner(person)).thenReturn(false);

        boolean result = peopleService.anyPersonIsValid();

        assertThat(result).isFalse();

        verify(personRepository, times(1)).findAll();

        verify(personMapper, atLeastOnce()).mapFrom(personEntity);
        verify(personValidator, times(1)).hasPartner(person);
        verify(personValidator, never()).hasThreeChildrenWithPartnerAsParent(person);
        verify(personValidator, never()).hasOneChildUnderEighteen(person);
    }

    @Test
    void anyPersonIsValid_hasThreeChildrenWithDifferentPartner() {
        PersonEntity personEntity = mock(PersonEntity.class);

        List<PersonEntity> personEntities = List.of(personEntity);

        Person person = mock(Person.class);

        when(personRepository.findAll()).thenReturn(personEntities);
        when(personMapper.mapFrom(any(PersonEntity.class)))
                .thenReturn(person);

        when(personValidator.hasPartner(person)).thenReturn(true);
        when(personValidator.hasThreeChildrenWithPartnerAsParent(person)).thenReturn(false);

        boolean result = peopleService.anyPersonIsValid();

        assertThat(result).isFalse();

        verify(personRepository, times(1)).findAll();

        verify(personMapper, atLeastOnce()).mapFrom(personEntity);
        verify(personValidator, times(1)).hasPartner(person);
        verify(personValidator, times(1)).hasThreeChildrenWithPartnerAsParent(person);
        verify(personValidator, never()).hasOneChildUnderEighteen(person);
    }

    @Test
    void anyPersonIsValid_hasOnlyChildrenAboveEighteen() {
        PersonEntity personEntity = mock(PersonEntity.class);

        List<PersonEntity> personEntities = List.of(personEntity);

        Person person = mock(Person.class);

        when(personRepository.findAll()).thenReturn(personEntities);
        when(personMapper.mapFrom(any(PersonEntity.class)))
                .thenReturn(person);

        when(personValidator.hasPartner(person)).thenReturn(true);
        when(personValidator.hasThreeChildrenWithPartnerAsParent(person)).thenReturn(true);
        when(personValidator.hasOneChildUnderEighteen(person)).thenReturn(false);

        boolean result = peopleService.anyPersonIsValid();

        assertThat(result).isFalse();

        verify(personRepository, times(1)).findAll();

        verify(personMapper, atLeastOnce()).mapFrom(personEntity);
        verify(personValidator, times(1)).hasPartner(person);
        verify(personValidator, times(1)).hasThreeChildrenWithPartnerAsParent(person);
        verify(personValidator, times(1)).hasOneChildUnderEighteen(person);
    }
}