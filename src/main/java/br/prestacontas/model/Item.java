package br.prestacontas.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Item {
	@Id
	@GeneratedValue
	private long id;
	private String descDespesa;
	private BigDecimal valorGasto;
	private Date dataDespesa;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = PrestacaoConta.class )
	private PrestacaoConta prestacaoConta;
	
	@OneToMany
	private List<Adiantamento> adiantamentos;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescDespesa() {
		return descDespesa;
	}

	public void setDescDespesa(String descDespesa) {
		this.descDespesa = descDespesa;
	}

	public BigDecimal getValorGasto() {
		return valorGasto;
	}

	public void setValorGasto(BigDecimal valorGasto) {
		this.valorGasto = valorGasto;
	}

	public Date getDataDespesa() {
		return dataDespesa;
	}

	public void setDataDespesa(Date dataDespesa) {
		this.dataDespesa = dataDespesa;
	}

	public PrestacaoConta getPrestacaoConta() {
		return prestacaoConta;
	}

	public void setPrestacaoConta(PrestacaoConta prestacaoConta) {
		this.prestacaoConta = prestacaoConta;
	}
	
}
