package br.com.castelo.projetojovemcientista.modelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.annotation.ManagedBean;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import br.com.castelo.projetojovemcientista.controller.NivelController;
import br.com.castelo.projetojovemcientista.model.Nivel;

@ManagedBean
public class NivelModelAssembler implements RepresentationModelAssembler<Nivel, EntityModel<Nivel>> {
	@Override
	public EntityModel<Nivel> toModel(Nivel nivel) {
		return EntityModel.of(nivel,
				linkTo(methodOn(NivelController.class).procurar(nivel.getId())).withSelfRel(),
				linkTo(methodOn(NivelController.class).listar()).withRel("nivel"));
	}
}
