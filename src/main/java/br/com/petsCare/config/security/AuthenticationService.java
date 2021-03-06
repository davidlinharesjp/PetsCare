package br.com.petsCare.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.petsCare.entities.User;
import br.com.petsCare.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByEmail(username);
		
		return user.orElseThrow(() -> new UsernameNotFoundException("Dados Inválidos"));
	}

}
