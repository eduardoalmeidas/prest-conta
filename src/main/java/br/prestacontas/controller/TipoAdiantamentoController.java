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

import br.prestacontas.model.TipoAdiantamento;
import br.prestacontas.services.TipoAdiantamentoService;


@RestController
@RequestMapping("/tipo")
public class TipoAdiantamentoController{

	@Autowired
	private TipoAdiantamentoService tipoAdiantamentoService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TipoAdiantamento>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(tipoAdiantamentoService.listar());
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@RequestBody TipoAdiantamento tipoAdiantamento) {
		tipoAdiantamento = tipoAdiantamentoService.salvar(tipoAdiantamento);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(tipoAdiantamento.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		TipoAdiantamento tipoAdiantamento = tipoAdiantamentoService.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(tipoAdiantamento);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		tipoAdiantamentoService.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody TipoAdiantamento tipoAdiantamento, 
			@PathVariable("id") Long id) {
		tipoAdiantamento.setId(id);
		tipoAdiantamentoService.atualizar(tipoAdiantamento);
		
		return ResponseEntity.noContent().build();
	}
	

}
