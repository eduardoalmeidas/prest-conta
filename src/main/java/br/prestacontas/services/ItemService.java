package br.prestacontas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.prestacontas.model.Item;
import br.prestacontas.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	
	public List<Item> listar() {
		return itemRepository.findAll();
	}
	
	public Item buscar(Long id) {
		Item item = itemRepository.findOne(id);
		
		if(item == null) {
			
		}
		
		return item;
	}
	
	public Item salvar(Item item) {
		//item.setId(null);
		return itemRepository.save(item);
	}
	
	public void deletar(Long id) {
		try {
			itemRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			//throw new UsuarioNaoEncontradoException("O Usuario não pôde ser encontrado.");
		}
	}
	
	public void atualizar(Item item) {
		verificarExistencia(item);
		itemRepository.save(item);
	}
	
	private void verificarExistencia(Item item) {
		buscar(item.getId());
	}

}
