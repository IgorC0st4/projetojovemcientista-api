package br.com.castelo.projetojovemcientista.modelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.annotation.ManagedBean;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import br.com.castelo.projetojovemcientista.controller.ResultadoController;
import br.com.castelo.projetojovemcientista.controller.UsuarioController;
import br.com.castelo.projetojovemcientista.model.Resultado;

@ManagedBean
public class ResultadoModelAssembler implements RepresentationModelAssembler<Resultado, EntityModel<Resultado>> {
	@Override
	public EntityModel<Resultado> toModel(Resultado resultado) {
		return EntityModel.of(resultado,
				linkTo(methodOn(ResultadoController.class).procurarResultadoMaisRapido(resultado.getNivel().getId())).withSelfRel(),
				linkTo(methodOn(ResultadoController.class).listar()).withRel("resultado"));
	}
	
}
