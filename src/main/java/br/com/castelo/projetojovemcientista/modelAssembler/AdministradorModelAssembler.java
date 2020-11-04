package br.com.castelo.projetojovemcientista.modelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.annotation.ManagedBean;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import br.com.castelo.projetojovemcientista.controller.AdministradorController;
import br.com.castelo.projetojovemcientista.model.Administrador;

@ManagedBean
public class AdministradorModelAssembler implements RepresentationModelAssembler<Administrador, EntityModel<Administrador>> {
	@Override
	public EntityModel<Administrador> toModel(Administrador administrador) {
		return EntityModel.of(administrador,
				linkTo(methodOn(AdministradorController.class).procurar(administrador.getId())).withSelfRel(),
				linkTo(methodOn(AdministradorController.class).listar()).withRel("administrador"));
	}
}
