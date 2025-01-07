package back.auth.review.security;

import back.auth.review.common.enumType.UserRoleType;
import back.auth.review.common.exception.AuthException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private static final int EXPIRATION_TIME = 1000 * 60 * 30;

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Value("${spring.jwt.secret}")
    private String secretKey;

    public boolean validateAccessToken(String accessToken){
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(accessToken);
            return false;
        } catch (ExpiredJwtException | IllegalStateException | IllegalArgumentException e) {
            // 토큰 만료 또는 토큰 변조시 예외 던지기
            throw new AuthException();
        }
    }

    public String createAccessToken(int userId, UserRoleType role){
        //access token
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .claim("role", role.toString())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token){
        String userId = decodeAccessToken(token);
        UserDetails userDetails = userDetailsServiceImpl
                .loadUserByUsername(userId);

        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    // 토큰의 Subject(userId) 반환
    private String decodeAccessToken(String accessToken) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(accessToken).getBody();
        return claims.getSubject();
    }
}
