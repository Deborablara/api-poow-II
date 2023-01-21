package br.ufsm.csi.app.security;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import br.ufsm.csi.app.model.UserModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtil {

  public static final long expiryTime = Duration.ofSeconds(60).toMillis();

  public String createToken(Optional<UserModel> user) {
    final Map<String, Object> claims = new HashMap<>();

    claims.put("Name", user.get().getUsername());
    claims.put("Permissions", user.get().getAuthorities());

    return Jwts.builder()
        .setClaims(claims)
        .setExpiration(new Date(System.currentTimeMillis() + expiryTime))
        .signWith(SignatureAlgorithm.HS256, "appPOOII")
        .compact();
  }
}
