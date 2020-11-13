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

import br.com.castelo.projetojovemcientista.exception.SenhaIncorretaException;
import br.com.castelo.projetojovemcientista.exception.UsuarioNotFoundException;
import br.com.castelo.projetojovemcientista.model.Usuario;
import br.com.castelo.projetojovemcientista.modelAssembler.UsuarioModelAssembler;
import br.com.castelo.projetojovemcientista.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
//@CrossOrigin(origins = "http://localhost:8100", allowedHeaders = "*")
@CrossOrigin(origins = "http://186.219.4.245:8100/", allowedHeaders = "*")
public class UsuarioController {
	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private UsuarioModelAssembler assembler;

	@GetMapping
	public CollectionModel<EntityModel<Usuario>> listar() {
		List<EntityModel<Usuario>> usuarios = repository.findAll().stream().map(assembler::toModel)
				.collect(Collectors.toList());
		return CollectionModel.of(usuarios, linkTo(methodOn(UsuarioController.class).listar()).withSelfRel());
	}

	@PostMapping("/cadastro")
	public ResponseEntity<?> salvar(@RequestBody Usuario novoUsuario) {
		EntityModel<Usuario> entityModel = assembler.toModel(repository.save(novoUsuario));
		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}
	
	@PostMapping("/login")
	public EntityModel<Usuario> efetuarLogin(@RequestBody Usuario usuarioLogin) {
		Usuario usuario = repository.findByNick(usuarioLogin.getNick()).orElseThrow(() -> new UsuarioNotFoundException(usuarioLogin.getNick()));
		if(usuario.getSenha().equals(usuarioLogin.getSenha())) {
			return assembler.toModel(usuario);
		}else {
			throw new SenhaIncorretaException();
		}
	}

	@GetMapping("/{id}")
	public EntityModel<Usuario> procurar(@PathVariable Long id) {
		Usuario usuario = repository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));
		return assembler.toModel(usuario);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@RequestBody Usuario novoUsuario, @PathVariable Long id) {
		Usuario usuarioAtualizado = repository.findById(id).map(usuario -> {
			usuario.setEscolaridade(novoUsuario.getEscolaridade());
			usuario.setGenero(novoUsuario.getGenero());
			usuario.setIdade(novoUsuario.getIdade());
			usuario.setNick(novoUsuario.getNick());

			return repository.save(usuario);
		}).orElseGet(() -> {
			novoUsuario.setId(id);
			return repository.save(novoUsuario);
		});

		EntityModel<Usuario> entityModel = assembler.toModel(usuarioAtualizado);
		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
