package br.com.castelo.projetojovemcientista.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import br.com.castelo.projetojovemcientista.model.Resultado;
import br.com.castelo.projetojovemcientista.repository.ResultadoRepository;
import br.com.castelo.projetojovemcientista.repository.UsuarioRepository;

@RestController
@RequestMapping("/resultado")
//@CrossOrigin(origins = "http://localhost:8100", allowedHeaders = "*")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class ResultadoController {
	@Autowired
	private ResultadoRepository resultadoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok(resultadoRepository.findAll());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> salvar(
			@RequestBody Resultado novoResultado) {
		return ResponseEntity.ok(resultadoRepository.save(novoResultado));
	}

	@PostMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> salvar(
			@RequestBody Resultado novoResultado,
			@PathVariable Long usuarioId) {
		novoResultado.setUsuario(usuarioRepository.findById(usuarioId).get());
		return ResponseEntity.ok(resultadoRepository.save(novoResultado));
	}

	@GetMapping("/maisRapido/{id}")
	public ResponseEntity<?> procurarResultadoMaisRapido(@PathVariable Long id) {
		Optional<Resultado> resultado = resultadoRepository.findResultadoMaisRapido(id);
		
		if(resultado.isPresent()) {
			return ResponseEntity.ok(resultado.get());
		}
		return ResponseEntity.notFound().build();
		
	}

}