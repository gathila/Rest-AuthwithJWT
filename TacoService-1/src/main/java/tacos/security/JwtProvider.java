package tacos.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

	
	public String generateJwtToken(Authentication authentication) {
		return "";
	}
	
	public boolean validateJwtToken(String token) {
		return true;
	}
	
	public String getUsernameFromToken(String jwt) {
		return "gathila";
	}
}
