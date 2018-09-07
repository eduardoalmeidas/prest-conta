package br.prestacontas.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.prestacontas.model.Adiantamento;
import br.prestacontas.model.Item;
import br.prestacontas.model.PrestacaoConta;
import br.prestacontas.model.TipoAdiantamento;
import br.prestacontas.services.AdiantamentoService;
import br.prestacontas.services.ItemService;
import br.prestacontas.services.PrestacaoContaService;
import br.prestacontas.services.TipoAdiantamentoService;


@RestController
@RequestMapping("/item")
public class ItemController{

	@Autowired
	private ItemService itemService;
	@Autowired
	private PrestacaoContaService prestacaoContaService;
	@Autowired
	private AdiantamentoService adiantamentosService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Item>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(itemService.listar());
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@RequestBody Item item) {
		
		
		PrestacaoConta prestconta = prestacaoContaService.buscar(item.getPrestacaoConta().getId());
		
		Adiantamento adiantamento = adiantamentosService.buscar(prestconta.getAdiantamento().getId());
		prestconta.setAdiantamento(adiantamento);
	
		item.setPrestacaoConta(prestconta);
		item = itemService.salvar(item);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(item.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		Item item = itemService.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(item);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		itemService.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Item item, 
			@PathVariable("id") Long id) {
		item.setId(id);
		itemService.atualizar(item);
		
		return ResponseEntity.noContent().build();
	}
	

}
