package sentosa.sentosaspringserver.global.security.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import sentosa.sentosaspringserver.global.security.auth.dto.TokenResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
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

	// ✅ Partner & Client 구분하여 토큰 생성
	public TokenResponse createTokenResponse(Long userId, String role) {
		return new TokenResponse(
			createToken(userId, role, ACCESS_TOKEN_VALIDITY_SECONDS),
			createToken(userId, role, REFRESH_TOKEN_VALIDITY_SECONDS)
		);
	}

	public String createToken(Long userId, String role, Long period) {
		return Jwts.builder()
			.subject(String.valueOf(userId))
			.claim("role", role)  // ✅ 역할 저장 (ROLE_PARTNER 또는 ROLE_CLIENT)
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
		return extractAllClaims(token).get("role", String.class); // ✅ "roles" → "role" 수정
	}

	public String getUserId(String token) {
		return extractAllClaims(token).getSubject();
	}

	public boolean validateToken(String token) {
		try {
			extractAllClaims(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
