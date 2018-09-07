package br.prestacontas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.prestacontas.model.Item;



public interface ItemRepository extends JpaRepository<Item, Long>{

}