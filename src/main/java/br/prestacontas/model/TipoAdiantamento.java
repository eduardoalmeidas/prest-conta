package br.prestacontas.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TipoAdiantamento {

	@Id
	@GeneratedValue
	private long id;
	private String descricaoAdiantamento;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescricaoAdiantamento() {
		return descricaoAdiantamento;
	}
	public void setDescricaoAdiantamento(String descricaoAdiantamento) {
		this.descricaoAdiantamento = descricaoAdiantamento;
	}
}
