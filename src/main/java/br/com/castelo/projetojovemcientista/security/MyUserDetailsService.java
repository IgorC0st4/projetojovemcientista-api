package br.com.castelo.projetojovemcientista.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.castelo.projetojovemcientista.model.Usuario;
import br.com.castelo.projetojovemcientista.repository.UsuarioRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = repository.findByNick(username);
		usuario.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
		return usuario.map(MyUserDetails::new).get();
	}

}
