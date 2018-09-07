package br.prestacontas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.prestacontas.model.PrestacaoConta;



public interface PrestacaoContasRepository extends JpaRepository<PrestacaoConta, Long>{

}