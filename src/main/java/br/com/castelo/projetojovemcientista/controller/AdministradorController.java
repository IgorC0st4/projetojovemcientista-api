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

import br.com.castelo.projetojovemcientista.exception.AdministradorNotFoundException;
import br.com.castelo.projetojovemcientista.model.Administrador;
import br.com.castelo.projetojovemcientista.modelAssembler.AdministradorModelAssembler;
import br.com.castelo.projetojovemcientista.repository.AdministradorRepository;

@RestController
@RequestMapping("/administrador")
public class AdministradorController {
	@Autowired
	private AdministradorRepository repository;
	@Autowired
	private AdministradorModelAssembler assembler;

	@GetMapping
	public CollectionModel<EntityModel<Administrador>> listar() {
		List<EntityModel<Administrador>> administradors = repository.findAll().stream().map(assembler::toModel)
				.collect(Collectors.toList());
		return CollectionModel.of(administradors, linkTo(methodOn(AdministradorController.class).listar()).withSelfRel());
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Administrador novoAdministrador) {
		EntityModel<Administrador> entityModel = assembler.toModel(repository.save(novoAdministrador));
		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}

	@GetMapping("/{id}")
	public EntityModel<Administrador> procurar(@PathVariable Long id) {
		Administrador administrador = repository.findById(id).orElseThrow(() -> new AdministradorNotFoundException(id));
		return assembler.toModel(administrador);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@RequestBody Administrador novoAdministrador, @PathVariable Long id) {
		Administrador administradorAtualizado = repository.findById(id).map(administrador -> {
			administrador.setEmail(novoAdministrador.getEmail());
			administrador.setSenha(novoAdministrador.getSenha());
			return repository.save(administrador);
		}).orElseGet(() -> {
			novoAdministrador.setId(id);
			return repository.save(novoAdministrador);
		});

		EntityModel<Administrador> entityModel = assembler.toModel(administradorAtualizado);
		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
