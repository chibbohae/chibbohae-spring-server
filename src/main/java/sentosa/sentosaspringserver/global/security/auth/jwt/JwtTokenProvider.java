package sentosa.sentosaspringserver.global.security.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import sentosa.sentosaspringserver.global.security.auth.dto.response.TokenResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class JwtTokenProvider {

	@Value("${jwt.secret}")
	private String secretKey;

	private final static long ACCESS_TOKEN_VALIDITY_SECONDS = 5 * 60 * 60; // 5시간
	private final static long REFRESH_TOKEN_VALIDITY_SECONDS = 10 * 60 * 60; // 10시간

	private Key key;
	private JwtParser jwtParser;

	@PostConstruct
	public void init() {
		this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
		this.jwtParser = Jwts.parser()
			.verifyWith((SecretKey) key)
			.build();
	}

	public TokenResponse createTokenResponse(Long userId, String username, String role) {
		return new TokenResponse(
			createToken(userId, username, role, ACCESS_TOKEN_VALIDITY_SECONDS),
			createToken(userId, username, role, REFRESH_TOKEN_VALIDITY_SECONDS)
		);
	}

	public String createToken(Long userId, String username, String role, Long period) {
		return Jwts.builder()
			.subject(username)
			.claim("userId", userId)
			.claim("roles", List.of(role))
			.issuedAt(new Date())
			.expiration(new Date(System.currentTimeMillis() + period * 1000))
			.signWith(key)
			.compact();
	}

	public Claims extractAllClaims(String token) {
		return jwtParser.parseSignedClaims(token)
			.getPayload();
	}

	public String getRole(String token) {
		return extractAllClaims(token).get("role", String.class);
	}

	public String getUserId(String token) {
		return extractAllClaims(token).get("userId", String.class);
	}

	public boolean validateToken(String token) {
		try {
			extractAllClaims(token);
			return true;
		} catch (ExpiredJwtException e) {
			log.warn("Expired JWT token: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			log.warn("Unsupported JWT token: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			log.warn("Invalid JWT token: {}", e.getMessage());
		} catch (Exception e) {
			log.warn("JWT validation failed: {}", e.getMessage());
		}
		return false;
	}
}
