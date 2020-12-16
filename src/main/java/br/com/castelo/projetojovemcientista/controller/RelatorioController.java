package br.com.castelo.projetojovemcientista.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/relatorio")
@CrossOrigin(allowedHeaders = "*", origins="*")
public class RelatorioController {
	@PersistenceContext
	private EntityManager manager;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		String queryStr = "SELECT nivel.numero as fase, nivel.parametro_inferior, nivel.parametro_superior, "
				+ "resultado.tempo_final, resultado.erros, usuario.nick, usuario.idade, usuario.escolaridade, "
				+ "usuario.sexo FROM resultado INNER JOIN usuario ON resultado.id_usuario = usuario.id "
				+ "INNER JOIN nivel ON resultado.id_nivel = nivel.id ORDER BY fase";
		TypedQuery<?> query = (TypedQuery<?>) manager.createQuery(queryStr);
		return ResponseEntity.ok(query.getResultList());
	}

}
