package aibg23.selection.service.implementation;

import aibg23.selection.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class TokenServiceImplementation implements TokenService {
    private Logger LOG = LoggerFactory.getLogger(TokenServiceImplementation.class);
    private String key = "secret_key";

    @Override
    public String generate(Claims claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    @Override
    public Claims parseToken(String jwt) {
        Claims claims;

        if (jwt.startsWith("Bearer")) {
            jwt = jwt.split(" ")[1];
        }

        try {
            claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jwt)
                    .getBody();

        } catch (Exception e) {
            LOG.info(e.getMessage());
            return null;
        }

        if (claims == null) {
            LOG.info("Nije moguće generisati Claims objekat");
            return null;
        }

        return claims;
    }
}
