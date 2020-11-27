package br.com.castelo.projetojovemcientista.controller;

import java.util.Optional;
import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import br.com.castelo.projetojovemcientista.model.Usuario;
import br.com.castelo.projetojovemcientista.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
//@CrossOrigin(origins = "http://localhost:8100", allowedHeaders = "*")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class UsuarioController {
	@Autowired
	private UsuarioRepository repository;

	@GetMapping
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok(repository.findAll());
	}

	@PostMapping("/cadastro")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> salvar(@RequestBody Usuario novoUsuario) throws SQLIntegrityConstraintViolationException {
		novoUsuario.setSenha(new BCryptPasswordEncoder().encode(novoUsuario.getSenha()));
		try {
			return ResponseEntity.ok(repository.save(novoUsuario));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Já há um usuário com o nick " + novoUsuario.getNick());
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> efetuarLogin(@RequestBody Usuario usuarioLogin) {
		Optional<Usuario> usuario = repository.findByNick(usuarioLogin.getNick());

		if (usuario.isPresent()) {
			if (new BCryptPasswordEncoder().matches(usuarioLogin.getSenha(), usuario.get().getSenha())) {
				return ResponseEntity.ok(usuario.get());
			} else {
				return ResponseEntity.badRequest().body("Senha incorreta");
			}
		}
		return ResponseEntity.notFound().build();
	}

}
