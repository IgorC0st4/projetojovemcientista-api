package br.com.castelo.projetojovemcientista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.castelo.projetojovemcientista.model.Resultado;

@Repository
public interface ResultadoRepository extends JpaRepository<Resultado, Long> {

}
