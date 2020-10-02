package br.com.pestCare.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.pestCare.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${jwt.expiration}")
	private String expiration;

	@Value("${jwt.secret}")
	private String key;

	public String generateToken(Authentication authentication) {

		Date today = new Date();
		Date dtExpired = new Date (today.getTime() + Long.parseLong(expiration));
		User user = (User) authentication.getPrincipal();
		return Jwts.builder()
				.setIssuer("Pest Care")
				.setSubject(user.getId().toString())
				.setIssuedAt(new Date())
				.setExpiration(dtExpired)
				.signWith(SignatureAlgorithm.HS256, key).compact();
	}

	public boolean isValidToken(String token) {
		try {
			Jwts.parser().setSigningKey(this.key).parseClaimsJws(token);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	public Long getIdUser(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.key).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
