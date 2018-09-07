package br.prestacontas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.prestacontas.model.Adiantamento;
import br.prestacontas.repository.AdiantamentosRepository;

@Service
public class AdiantamentoService {

	@Autowired
	private AdiantamentosRepository adiantamentosRepository;
	
	
	public List<Adiantamento> listar() {
		return adiantamentosRepository.findAll();
	}
	
	public Adiantamento buscar(Long id) {
		Adiantamento adiantamento = adiantamentosRepository.findOne(id);
		
		if(adiantamento == null) {
		}
		
		return adiantamento;
	}
	
	public Adiantamento salvar(Adiantamento adiantamento) {
		return adiantamentosRepository.save(adiantamento);
	}
	
	public void deletar(Long id) {
		try {
			adiantamentosRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
		}
	}
	
	public void atualizar(Adiantamento adiantamento) {
		verificarExistencia(adiantamento);
		adiantamentosRepository.save(adiantamento);
	}
	
	private void verificarExistencia(Adiantamento adiantamento) {
		buscar(adiantamento.getId());
	}

}
