package br.com.castelo.projetojovemcientista.modelAssembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import javax.annotation.ManagedBean;

import br.com.castelo.projetojovemcientista.controller.UsuarioController;
import br.com.castelo.projetojovemcientista.model.Usuario;

@ManagedBean
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {
	@Override
	public EntityModel<Usuario> toModel(Usuario usuario) {
		return EntityModel.of(usuario,
				linkTo(methodOn(UsuarioController.class).procurar(usuario.getId())).withSelfRel(),
				linkTo(methodOn(UsuarioController.class).listar()).withRel("usuario"));
	}
}
