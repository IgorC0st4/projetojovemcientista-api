package br.com.castelo.projetojovemcientista.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.castelo.projetojovemcientista.exception.ResultadoNotFoundException;
import br.com.castelo.projetojovemcientista.model.Resultado;
import br.com.castelo.projetojovemcientista.modelAssembler.ResultadoModelAssembler;
import br.com.castelo.projetojovemcientista.repository.ResultadoRepository;

@RestController
@RequestMapping("/resultado")
//@CrossOrigin(origins = "http://localhost:8100", allowedHeaders = "*")
@CrossOrigin(origins = "http://186.219.4.245:8100/", allowedHeaders = "*")
public class ResultadoController {
	@Autowired
	private ResultadoRepository repository;
	@Autowired
	private ResultadoModelAssembler assembler;

	@GetMapping
	public CollectionModel<EntityModel<Resultado>> listar() {
		List<EntityModel<Resultado>> resultados = repository.findAll().stream().map(assembler::toModel)
				.collect(Collectors.toList());
		return CollectionModel.of(resultados, linkTo(methodOn(ResultadoController.class).listar()).withSelfRel());
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Resultado novoResultado) {
		EntityModel<Resultado> entityModel = assembler.toModel(repository.save(novoResultado));
		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}

	@GetMapping("/maisRapido/{id}")
	public EntityModel<Resultado> procurarResultadoMaisRapido(@PathVariable Long id) {
		Resultado resultado = repository.findResultadoMaisRapido(id);
		return assembler.toModel(resultado);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@RequestBody Resultado novoResultado, @PathVariable Long id) {
		Resultado resultadoAtualizado = repository.findById(id).map(resultado -> {
			resultado.setNivel(novoResultado.getNivel());
			resultado.setTempoFinal(novoResultado.getTempoFinal());
			resultado.setUsuario(novoResultado.getUsuario());
			return repository.save(resultado);
		}).orElseGet(() -> {
			novoResultado.setId(id);
			return repository.save(novoResultado);
		});

		EntityModel<Resultado> entityModel = assembler.toModel(resultadoAtualizado);
		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}