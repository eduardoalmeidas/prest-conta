package br.prestacontas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.prestacontas.model.TipoAdiantamento;
import br.prestacontas.repository.TipoAdiantamentoRepository;

@Service
public class TipoAdiantamentoService {

	@Autowired
	private TipoAdiantamentoRepository tipoAdiantamentosRepository;
	
	
	public List<TipoAdiantamento> listar() {
		return tipoAdiantamentosRepository.findAll();
	}
	
	public TipoAdiantamento buscar(Long id) {
		TipoAdiantamento tipoAdiantamento = tipoAdiantamentosRepository.findOne(id);
		
		if(tipoAdiantamento == null) {
		}
		
		return tipoAdiantamento;
	}
	
	public TipoAdiantamento salvar(TipoAdiantamento tipoAdiantamento) {
		//tipoAdiantamento.setId();
		return tipoAdiantamentosRepository.save(tipoAdiantamento);
	}
	
	public void deletar(Long id) {
		try {
			tipoAdiantamentosRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
		}
	}
	
	public void atualizar(TipoAdiantamento tipoAdiantamento) {
		verificarExistencia(tipoAdiantamento);
		tipoAdiantamentosRepository.save(tipoAdiantamento);
	}
	
	private void verificarExistencia(TipoAdiantamento tipoAdiantamento) {
		buscar(tipoAdiantamento.getId());
	}

}
