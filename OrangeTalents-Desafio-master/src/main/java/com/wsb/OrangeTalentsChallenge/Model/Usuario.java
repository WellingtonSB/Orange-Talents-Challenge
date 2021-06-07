package com.wsb.OrangeTalentsChallenge.Model;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank
	private String nome;

	@NotBlank
	@Email
	private String email;

	@CPF
	@NotBlank
	private String cpf;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataNascimento;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Veiculo> veiculo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Veiculo> getVeiculos() {
		return veiculo;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculo = veiculos;
	}

}
