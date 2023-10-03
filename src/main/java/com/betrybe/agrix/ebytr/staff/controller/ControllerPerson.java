package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.dto.PersonDto;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import com.betrybe.agrix.ebytr.staff.util.PersonResponse;
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
  public ResponseEntity<PersonResponse> createNewPerson(@RequestBody PersonDto personDto) {
    Person person = personDto.toEntity();
    Person newPerson = personService.create(person);
    PersonResponse personResponse = new PersonResponse(newPerson);
    
    return ResponseEntity.status(HttpStatus.CREATED).body(personResponse);
  }
}
