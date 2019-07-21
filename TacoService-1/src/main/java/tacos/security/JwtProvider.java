package tacos.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tacos.model.CustomUserDetails;

@Component
public class JwtProvider {
	
	@Value("${taco.app.jwtSerect}")
	private String jwtSerect;

	@Value("${taco.app.jwtExpiration}")
	private int jwtExpiration;
	
	public String generateJwtToken(Authentication authentication) {
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
		return Jwts.builder()
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpiration * 1000))
				.signWith(SignatureAlgorithm.HS512, jwtSerect)
				.compact();
	}
	
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSerect).parseClaimsJws(authToken);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String getUsernameFromToken(String token) {
		return Jwts.parser()
				.setSigningKey(jwtSerect)
				.parseClaimsJws(token)
				.getBody().getSubject();
	}
}
