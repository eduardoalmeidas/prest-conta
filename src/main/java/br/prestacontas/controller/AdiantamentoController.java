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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.prestacontas.model.Adiantamento;
import br.prestacontas.model.TipoAdiantamento;
import br.prestacontas.model.Usuario;
import br.prestacontas.services.AdiantamentoService;
import br.prestacontas.services.TipoAdiantamentoService;
import br.prestacontas.services.UsuarioService;


@RestController
@RequestMapping("/adiantamento")
public class AdiantamentoController{

	@Autowired
	private AdiantamentoService adiantamentosService;
	@Autowired
	private UsuarioService usuariosService;
	@Autowired
	private TipoAdiantamentoService tipoService;


	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Adiantamento>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(adiantamentosService.listar());
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@RequestBody Adiantamento adiantamento) {
		
		
		Usuario user = usuariosService.buscar(adiantamento.getUsuario().getId());
		adiantamento.setUsuario(user);
		TipoAdiantamento tipo = tipoService.buscar(adiantamento.getTipoAdiantamento().getId());
		adiantamento.setTipoAdiantamento(tipo);
		adiantamento = adiantamentosService.salvar(adiantamento);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(adiantamento.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		Adiantamento adiantamento = adiantamentosService.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(adiantamento);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		adiantamentosService.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Adiantamento adiantamento, 
			@PathVariable("id") Long id) {
		adiantamento.setId(id);
		adiantamentosService.atualizar(adiantamento);
		
		return ResponseEntity.noContent().build();
	}
	

}
