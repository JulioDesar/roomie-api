package com.senac.roomie.dto;

import org.springframework.data.domain.Page;

import com.senac.roomie.model.TipoUsuario;
import com.senac.roomie.model.Usuario;

public class UsuarioDto {

    private Integer id;
    private String nome;
    private Boolean ativo;
    private TipoUsuario funcao;

    public UsuarioDto(Usuario user) {
        this.id = user.getId();
        this.nome = user.getNome();
        this.ativo = user.getAtivo();
        this.funcao = user.getFuncao();
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public TipoUsuario getFuncao() {
        return funcao;
    }

    public static Page<UsuarioDto> convert(Page<Usuario> usuarios) {
        return usuarios.map(UsuarioDto::new);
    }

}
