package br.ufsm.csi.app.security;

import br.ufsm.csi.app.model.UserModel;
import io.jsonwebtoken.*;

import org.springframework.security.core.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JwtUtil {

    private final String secret_key = "31d6cfe0d16ae931b73c59d7e0c089c0";

    private long accessTokenValidity = 60*60*1000;

    private  JwtParser jwtParser;

    private final String TOKEN_HEADER = "Authorization";

    private final String TOKEN_PREFIX = "Bearer";

    public  JwtUtil(){
        this.jwtParser = Jwts.parser().setSigningKey(secret_key);
    }

    public String createToken(UserModel user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("userId", user.getUserId());
        claims.put("roles", user.getAuthorities());
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .compact();
    }

    private Claims parseJwtClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token != null) {
                return parseJwtClaims(token);
            }
            return null;
        } catch (ExpiredJwtException ex) {
            req.setAttribute("expired", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            req.setAttribute("invalid", ex.getMessage());
            throw ex;
        }
    }

    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    public boolean validateClaims(Claims claims) throws AuthenticationException {
        try {
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            throw e;
        }
    }


}
