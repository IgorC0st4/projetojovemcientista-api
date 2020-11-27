package br.com.castelo.projetojovemcientista.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.castelo.projetojovemcientista.model.Resultado;

@Repository
public interface ResultadoRepository extends JpaRepository<Resultado, Long> {

	@Query("SELECT tempoFinal FROM Resultado WHERE id_nivel = ?1 ORDER BY tempo_final ASC")
	Optional<Resultado> findResultadoMaisRapido(Long idNivel);
}
