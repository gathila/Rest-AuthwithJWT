package tacos.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tacos.domain.User;
import tacos.model.LoginForm;
import tacos.repo.UserRepository;
import tacos.security.JwtProvider;

@RestController
@RequestMapping(path="/signIn", produces="application/json")
@CrossOrigin(origins="*")
public class UserLoginControllerAPI {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtProvider jwtProvider;

	@PostMapping
	public ResponseEntity<?> signInUser(@RequestBody LoginForm loginForm){
		
		Authentication authentication = authenticationManager.
				authenticate(new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtProvider.generateJwtToken(authentication);
		
		return new ResponseEntity<User>(new User(), HttpStatus.ACCEPTED);
	}

	@GetMapping
	public ResponseEntity<User> loginUser(/* @RequestBody User user */){
		return new ResponseEntity<User>(new User(), HttpStatus.UNAUTHORIZED);
	}
	
}
