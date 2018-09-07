package br.prestacontas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.prestacontas.model.Usuario;
import br.prestacontas.repository.UsuariosRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuariosRepository usuariosRepository;
	
	
	public List<Usuario> listar() {
		return usuariosRepository.findAll();
	}
	
	public Usuario buscar(Long id) {
		Usuario usuario = usuariosRepository.findOne(id);
		
		if(usuario == null) {
			//throw new UsuarioNaoEncontradoException("O livro não pôde ser encontrado.");
		}
		
		return usuario;
	}
	
	public Usuario salvar(Usuario usuario) {
		//usuario.setId(1);
		return usuariosRepository.save(usuario);
	}
	
	public void deletar(Long id) {
		try {
			usuariosRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			//throw new UsuarioNaoEncontradoException("O Usuario não pôde ser encontrado.");
		}
	}
	
	public void atualizar(Usuario usuario) {
		verificarExistencia(usuario);
		usuariosRepository.save(usuario);
	}
	
	private void verificarExistencia(Usuario usuario) {
		buscar(usuario.getId());
	}

}
