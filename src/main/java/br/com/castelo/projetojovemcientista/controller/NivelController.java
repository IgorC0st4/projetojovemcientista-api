package br.com.castelo.projetojovemcientista.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.castelo.projetojovemcientista.model.Nivel;
import br.com.castelo.projetojovemcientista.repository.NivelRepository;

@RestController
@RequestMapping("/nivel")
//@CrossOrigin(origins = "http://localhost:8100", allowedHeaders = "*")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class NivelController {
	@Autowired
	private NivelRepository repository;

	@GetMapping
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok(repository.findAll());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> salvar(@RequestBody Nivel novoNivel) {
		return ResponseEntity.ok(repository.save(novoNivel));
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> atualizar(@RequestBody Nivel novoNivel, @PathVariable Long id) {
		Nivel nivelAtualizado = repository.findById(id).map(nivel -> {
			nivel.setNumero(novoNivel.getNumero());
			nivel.setParametroInferior(novoNivel.getParametroInferior());
			nivel.setParametroSuperior(novoNivel.getParametroSuperior());

			return repository.save(nivel);
		}).orElseGet(() -> {
			novoNivel.setId(id);
			return repository.save(novoNivel);
		});

		return ResponseEntity.ok(nivelAtualizado);
	}

}
