package br.com.castelo.projetojovemcientista.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.castelo.projetojovemcientista.model.Administrador;
import br.com.castelo.projetojovemcientista.repository.AdministradorRepository;

@RestController
@RequestMapping("/administrador")
public class AdministradorController {
	@Autowired
	private AdministradorRepository repository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> salvar(@RequestBody Administrador novoAdministrador) {
		return ResponseEntity.ok(repository.save(novoAdministrador));
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> atualizar(@RequestBody Administrador novoAdministrador, @PathVariable Long id) {
		Administrador administradorAtualizado = repository.findById(id).map(administrador -> {
			administrador.setEmail(novoAdministrador.getEmail());
			administrador.setSenha(novoAdministrador.getSenha());
			return repository.save(administrador);
		}).orElseGet(() -> {
			novoAdministrador.setId(id);
			return repository.save(novoAdministrador);
		});
		return ResponseEntity.ok(administradorAtualizado);
	}

}
