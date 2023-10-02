package com.betrybe.agrix.solution;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.exception.PersonNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class PersonServiceTest {

  @Mock
  private PersonRepository personRepository;

  private PersonService personService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    personService = new PersonService(personRepository);
  }

  @Test
  public void testGetPersonById_ExistingPerson_ReturnsPerson() {
    Long id = 1L;
    Person expectedPerson = new Person();
    expectedPerson.setId(id);
    Mockito.when(personRepository.findById(id)).thenReturn(Optional.of(expectedPerson));

    Person actualPerson = personService.getPersonById(id);

    assertEquals(expectedPerson, actualPerson);
  }

  @Test
  public void testGetPersonById_PersonNotFound_ThrowsException() {
    Long id = 2L;
    Mockito.when(personRepository.findById(id)).thenReturn(Optional.empty());

    assertThrows(PersonNotFoundException.class, () -> personService.getPersonById(id));
  }

  @Test
  public void testGetPersonByUsername_ExistingPerson_ReturnsPerson() {
    String username = "testUser";
    Person expectedPerson = new Person();
    expectedPerson.setUsername(username);
    Mockito.when(personRepository.findByUsername(username)).thenReturn(expectedPerson);

    UserDetails actualPerson = personService.loadUserByUsername(username);

    assertEquals(expectedPerson, actualPerson);
  }

  @Test
  public void testGetPersonByUsername_PersonNotFound_ThrowsException() {
    String username = "nonExistingUser";
    Mockito.when(personRepository.findByUsername(username)).thenReturn(null);

    assertThrows(PersonNotFoundException.class, () -> personService.loadUserByUsername(username));
  }

  @Test
  public void testCreatePerson() {
    Person personToCreate = new Person();
    Mockito.when(personRepository.save(personToCreate)).thenReturn(personToCreate);

    Person createdPerson = personService.create(personToCreate);

    assertNotNull(createdPerson);
  }
}
