package com.senac.roomie.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "usuario")
public class Usuario {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "CPF")
	private Long CPF;

	@Column(name = "nome")
	private String nome;

	@Column(name = "telefone")
	private String telefone;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_nascimento")
	private LocalDate nascimento;

	@Column(name = "ativo")
	private Boolean ativo = true;

	@Enumerated(EnumType.STRING)
	@Column(name = "funcao")
	private TipoUsuario funcao;

	@Column(name = "email")
	private String email;

	@Column(name = "senha")
	private String senha;

	public Integer getId() {
		return id;
	}

	public long getCPF() {
		return CPF;
	}
	
	public void setCPF(Long cPF) {
		CPF = cPF;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public TipoUsuario getFuncao() {
		return funcao;
	}

	public void setFuncao(TipoUsuario funcao) {
		this.funcao = funcao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public Usuario() {
	}

	public Usuario(Long CPF, String nome, String telefone, LocalDate nascimento, TipoUsuario funcao, String email,
			String senha) {
		this.CPF = CPF;
		this.nome = nome;
		this.telefone = telefone;
		this.nascimento = nascimento;
		this.funcao = funcao;
		this.email = email;
		this.senha = senha;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}

}
