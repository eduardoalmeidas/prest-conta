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
import br.prestacontas.model.PrestacaoConta;
import br.prestacontas.services.AdiantamentoService;
import br.prestacontas.services.PrestacaoContaService;


@RestController
@RequestMapping("/prestacao-conta")
public class PrestacaoContaController{

	@Autowired
	private PrestacaoContaService prestacaoContaService;
	@Autowired
	private AdiantamentoService adiantamentosService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PrestacaoConta>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(prestacaoContaService.listar());
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@RequestBody PrestacaoConta prestacaoConta) {
		
		Adiantamento adiantamento = adiantamentosService.buscar(prestacaoConta.getAdiantamento().getId());
		prestacaoConta.setAdiantamento(adiantamento);
		prestacaoConta = prestacaoContaService.salvar(prestacaoConta);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(prestacaoConta.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		PrestacaoConta prestacaoConta = prestacaoContaService.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(prestacaoConta);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		prestacaoContaService.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody PrestacaoConta prestacaoConta, 
			@PathVariable("id") Long id) {
		prestacaoConta.setId(id);
		prestacaoContaService.atualizar(prestacaoConta);
		
		return ResponseEntity.noContent().build();
	}
	

}
