package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.dto.AuthResponseDto;
import com.betrybe.agrix.ebytr.staff.dto.AuthenticationDto;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import com.betrybe.agrix.ebytr.staff.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Crontroller Authentication.
 */
@RestController
@RequestMapping("/auth")
public class ControllerAuthentication {
  private final AuthenticationManager authenticationManager;

  private final TokenService tokenService;

  @Autowired
  public ControllerAuthentication(
        AuthenticationManager authenticationManager,
        TokenService tokenService
  ) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  /**
   * POST LOGIN.
   *
   */
  @PostMapping("/login")
  public ResponseEntity<AuthResponseDto> login(
        @RequestBody AuthenticationDto authenticationDto
  ) {
    UsernamePasswordAuthenticationToken usernamePassword =
        new UsernamePasswordAuthenticationToken(
            authenticationDto.username(),
            authenticationDto.password()
        );

    Authentication auth = authenticationManager.authenticate(usernamePassword);
    Person person = (Person) auth.getPrincipal();
    String token = tokenService.generateToken(person);


    AuthResponseDto response = new AuthResponseDto(token);

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
