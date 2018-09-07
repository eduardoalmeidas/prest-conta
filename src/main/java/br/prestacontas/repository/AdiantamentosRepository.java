package br.prestacontas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.prestacontas.model.Adiantamento;



public interface AdiantamentosRepository extends JpaRepository<Adiantamento, Long>{

}