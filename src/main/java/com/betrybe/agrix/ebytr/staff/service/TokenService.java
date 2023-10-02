package com.betrybe.agrix.ebytr.staff.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
  * class TokenService.
  */
@Service
public class TokenService {
  @Value("${api.security.token.secret}")

  private String secret;


  /**
  * generateToken.
  */
  public String generateToken(Person person) {
    Algorithm algorithm = Algorithm.HMAC256(secret);

    return JWT.create()
        .withIssuer("trybetrack")
        .withSubject(person.getUsername())
        .withExpiresAt(generateExpirationDate())
        .sign(algorithm);
  }

  private Instant generateExpirationDate() {
    return LocalDateTime.now()
        .plusHours(2)
        .toInstant(ZoneOffset.of("-03:00"));
  }

  /**
  * validateToken.
  */
  public String validateToken(String token) {
    Algorithm algorithm = Algorithm.HMAC256(secret);

    return JWT.require(algorithm)
        .withIssuer("trybetrack")
        .build()
        .verify(token)
        .getSubject();
  }
}
