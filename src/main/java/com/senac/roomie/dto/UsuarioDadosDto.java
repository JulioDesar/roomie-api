package com.senac.roomie.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.senac.roomie.config.validacao.Cpf;
import com.senac.roomie.model.TipoUsuario;
import com.senac.roomie.model.Usuario;

public class UsuarioDadosDto {

	@NotNull @NotEmpty
	private String nome;
	
	@JsonProperty @Cpf
	private String CPF;
	
	@NotNull @NotEmpty
	private String telefone;
	
	@NotNull
	private LocalDate nascimento;
	
	@NotNull
	private TipoUsuario funcao;
	
	@NotNull @NotEmpty @Email
	private String email;
	
	@NotNull @NotEmpty
	private String senha;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(Long CPF) {
		String string = CPF.toString();
		this.CPF = string;
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

	public Usuario convert() {
		long cpf = Long.parseLong(CPF);
		System.out.println(cpf);
		return new Usuario(cpf, nome, telefone, nascimento, funcao, email, senha);
	}

}
