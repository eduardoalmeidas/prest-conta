package br.prestacontas.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class PrestacaoConta {
	@Id
	@GeneratedValue
	private long id;
	private Date dataPrestacao;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Adiantamento.class)
	private Adiantamento adiantamento;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDataPrestacao() {
		return dataPrestacao;
	}
	public void setDataPrestacao(Date dataPrestacao) {
		this.dataPrestacao = dataPrestacao;
	}
	public Adiantamento getAdiantamento() {
		return adiantamento;
	}
	public void setAdiantamento(Adiantamento adiantamento) {
		this.adiantamento = adiantamento;
	}
	
}
