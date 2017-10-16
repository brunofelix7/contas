package br.com.caelum.contas.model;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Conta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "{conta.descricao.vazio}")
	@Size(min = 5, message = "{conta.descricao.tamanho}")
	private String descricao;
	
	private boolean paga;
	
	@NotNull(message = "{conta.valor.vazio}")
	private double valor;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar dataPagamento;
	
	private TipoDaConta tipo;
	
	public Conta(){
		
	}

	public Conta(Long id, String descricao, boolean paga, double valor, Calendar dataPagamento, TipoDaConta tipo) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.paga = paga;
		this.valor = valor;
		this.dataPagamento = dataPagamento;
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isPaga() {
		return paga;
	}

	public void setPaga(boolean paga) {
		this.paga = paga;
	}

	public Calendar getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Calendar dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public TipoDaConta getTipo() {
		return tipo;
	}

	public void setTipo(TipoDaConta tipo) {
		this.tipo = tipo;
	}

	public double getValor() {
		return valor;
	}	
	
	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Conta [id=" + id + ", descricao=" + descricao + ", paga=" + paga + ", valor=" + valor
				+ ", dataPagamento=" + dataPagamento + ", tipo=" + tipo + "]";
	}
	
}
