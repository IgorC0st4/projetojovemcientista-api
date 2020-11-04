package br.com.castelo.projetojovemcientista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.castelo.projetojovemcientista.model.Nivel;

@Repository
public interface NivelRepository extends JpaRepository<Nivel, Long> {

}
