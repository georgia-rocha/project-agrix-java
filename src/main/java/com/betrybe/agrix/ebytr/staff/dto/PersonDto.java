package com.betrybe.agrix.ebytr.staff.dto;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * Dto Person.
 */
public record PersonDto(
      Long id,
      String username,
      String password,
      Role role
) {
  /**
  * Entity de Person.
  */
  public Person toEntity() {
    Person person = new Person();

    person.setId(id);
    person.setUsername(username);
    person.setPassword(password);
    person.setRole(role);

    return person;
  }
}
