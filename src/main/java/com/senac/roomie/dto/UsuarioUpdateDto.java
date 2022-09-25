package com.senac.roomie.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.senac.roomie.model.TipoUsuario;
import com.senac.roomie.model.Usuario;
import com.senac.roomie.repository.UsuarioRepository;

public class UsuarioUpdateDto {

	@NotNull @NotEmpty
	private String telefone;
	
	@NotNull
	private Boolean ativo;
	
	@NotNull
	private TipoUsuario funcao;

	public UsuarioUpdateDto() {
	}

	public UsuarioUpdateDto(Usuario user) {
		this.telefone = user.getTelefone();
		this.ativo = user.getAtivo();
		this.funcao = user.getFuncao();
	}

	public String getTelefone() {
		return telefone;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public TipoUsuario getFuncao() {
		return funcao;
	}

	public Usuario atualizar(Integer id, UsuarioRepository bd) {
		Usuario user = bd.getReferenceById(id);

		user.setTelefone(this.telefone);
		user.setAtivo(getAtivo());
		user.setFuncao(this.funcao);
		
		bd.save(user);

		return user;
	}

}
