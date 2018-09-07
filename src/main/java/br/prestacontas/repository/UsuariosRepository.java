package br.prestacontas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.prestacontas.model.Usuario;



public interface UsuariosRepository extends JpaRepository<Usuario, Long>{

}