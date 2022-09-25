package com.senac.roomie.dto;

import java.time.LocalDate;

import com.senac.roomie.model.TipoUsuario;
import com.senac.roomie.model.Usuario;

public class UsuarioAllDto {

	private Integer id;
	private Long CPF;
	private String nome;
	private String telefone;
	private LocalDate nascimento;
	private Boolean ativo;
	private TipoUsuario funcao;
	private String email;

	public UsuarioAllDto(Usuario user) {
		this.id = user.getId();
		CPF = user.getCPF();
		this.nome = user.getNome();
		this.telefone = user.getTelefone();
		this.nascimento = user.getNascimento();
		this.ativo = user.getAtivo();
		this.funcao = user.getFuncao();
		this.email = user.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public Long getCPF() {
		return CPF;
	}

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public LocalDate getNascimento() {
		return nascimento;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public TipoUsuario getFuncao() {
		return funcao;
	}

	public String getEmail() {
		return email;
	}

}
