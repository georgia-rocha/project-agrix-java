package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.dto.PersonDto;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller Person.
 */
@RestController
@RequestMapping(value = "/persons")
public class ControllerPerson {
  private final PersonService personService;

  @Autowired
  public ControllerPerson(PersonService personService) {
    this.personService = personService;
  }

  /**
  * POST.
  */
  @PostMapping()
  public ResponseEntity<PersonDto> createNewPerson(@RequestBody PersonDto personDto) {
    Person person = personDto.toEntity();
    Person newPerson = personService.create(person);

    PersonDto p = new PersonDto(
        newPerson.getId(),
        newPerson.getUsername(),
        newPerson.getPassword(),
        newPerson.getRole()
     );
    return ResponseEntity.status(HttpStatus.CREATED).body(p);
  }
}
