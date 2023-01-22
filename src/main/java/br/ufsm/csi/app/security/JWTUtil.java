package br.ufsm.csi.app.security;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import br.ufsm.csi.app.model.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtil {

  public static final long expiryTime = Duration.ofSeconds(60).toMillis();

  public String createToken(Optional<UserModel> user) {
    final Map<String, Object> claims = new HashMap<>();

    claims.put("sub", user.get().getUsername());

    Object[] permission = user.get().getAuthorities().toArray();
    System.out.println("Permission: " + permission.length);
    claims.put("permissions", user.get().getAuthorities());

    return Jwts.builder()
        .setClaims(claims)
        .setExpiration(new Date(System.currentTimeMillis() + expiryTime))
        .signWith(SignatureAlgorithm.HS256, "appPOOII")
        .compact();
  }

  public Claims parseToken(String token) {
    return Jwts.parser()
        .setSigningKey("appPOOII")
        .parseClaimsJws(token.replace("Bearer", ""))
        .getBody();
  }

  public String getUserNameToken(String token) {
    if (token != null) {
      return this.parseToken(token).getSubject();
    } else {
      return null;
    }
  }

  public boolean isTokenExpired(String token) {
    return this.parseToken(token).getExpiration().before(new Date());
  }
}
