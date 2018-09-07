package br.prestacontas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.prestacontas.model.PrestacaoConta;
import br.prestacontas.repository.PrestacaoContasRepository;

@Service
public class PrestacaoContaService {

	@Autowired
	private PrestacaoContasRepository prestacaoContasRepository;
	
	
	public List<PrestacaoConta> listar() {
		return prestacaoContasRepository.findAll();
	}
	
	public PrestacaoConta buscar(Long id) {
		PrestacaoConta prestacaoConta = prestacaoContasRepository.findOne(id);
		
		if(prestacaoConta == null) {
			
		}
		
		return prestacaoConta;
	}
	
	public PrestacaoConta salvar(PrestacaoConta prestacaoConta) {
	//	prestacaoConta.setId(null);
		return prestacaoContasRepository.save(prestacaoConta);
	}
	
	public void deletar(Long id) {
		try {
			prestacaoContasRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			//throw new UsuarioNaoEncontradoException("O Usuario não pôde ser encontrado.");
		}
	}
	
	public void atualizar(PrestacaoConta prestacaoConta) {
		verificarExistencia(prestacaoConta);
		prestacaoContasRepository.save(prestacaoConta);
	}
	
	private void verificarExistencia(PrestacaoConta prestacaoConta) {
		buscar(prestacaoConta.getId());
	}

}
