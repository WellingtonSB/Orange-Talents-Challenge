package com.wsb.OrangeTalentsChallenge.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "veiculos")
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	@NotBlank
	private String marca;
	@NotBlank
	private String modelo;// carros
	@NotBlank
	private String anoModelo;// ano
	
    private String preco;
	private String rodizio;
	private boolean rodizioAtivo;

	@ManyToOne
	@JsonIgnoreProperties("veiculos")
	private Usuario usuario;

	public String getAnoModelo() {
		return anoModelo;
	}

	public void setAnoModelo(String anoModelo) {
		this.anoModelo = anoModelo;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getRodizio() {
		return rodizio;
	}

	public void setRodizio(String rodizio) {
		this.rodizio = rodizio;
	}

	public boolean isRodizioAtivo() {
		return rodizioAtivo;
	}

	public void setRodizioAtivo(boolean rodizioAtivo) {
		this.rodizioAtivo = rodizioAtivo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	
}
