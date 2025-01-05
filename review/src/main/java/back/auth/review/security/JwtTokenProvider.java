package back.auth.review.security;

import back.auth.review.common.enumType.UserRoleType;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final int EXPIRATION_TIME = 1000 * 60 * 30;

    @Value("${spring.jwt.secret}")
    private String secretKey;

    public String createAccessToken(String email, UserRoleType role){

        //access token
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role.toString())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
