package com.senac.roomie.dto;

public class TokenDto {

	private String token;
	private String tipo;
	private UsuarioAllDto user;

	public TokenDto(String token, String tipo, UsuarioAllDto user) {
		this.token = token;
		this.tipo = tipo;
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}
	
	public UsuarioAllDto getUser() {
		return user;
	}

}
