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

import br.com.castelo.projetojovemcientista.exception.NivelNotFoundException;
import br.com.castelo.projetojovemcientista.model.Nivel;
import br.com.castelo.projetojovemcientista.modelAssembler.NivelModelAssembler;
import br.com.castelo.projetojovemcientista.repository.NivelRepository;

@RestController
@RequestMapping("/nivel")
//@CrossOrigin(origins = "http://localhost:8100", allowedHeaders = "*")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class NivelController {
	@Autowired
	private NivelRepository repository;
	@Autowired
	private NivelModelAssembler assembler;

	@GetMapping
	public CollectionModel<EntityModel<Nivel>> listar() {
		List<EntityModel<Nivel>> nivels = repository.findAll().stream().map(assembler::toModel)
				.collect(Collectors.toList());
		return CollectionModel.of(nivels, linkTo(methodOn(NivelController.class).listar()).withSelfRel());
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Nivel novoNivel) {
		EntityModel<Nivel> entityModel = assembler.toModel(repository.save(novoNivel));
		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}

	@GetMapping("/{id}")
	public EntityModel<Nivel> procurar(@PathVariable Long id) {
		Nivel nivel = repository.findById(id).orElseThrow(() -> new NivelNotFoundException(id));
		return assembler.toModel(nivel);
	}
	
	@PutMapping("/{id}")
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

		EntityModel<Nivel> entityModel = assembler.toModel(nivelAtualizado);
		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
