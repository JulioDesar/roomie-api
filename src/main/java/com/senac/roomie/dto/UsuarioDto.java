package com.senac.roomie.dto;

import java.util.List;
import java.util.stream.Collectors;

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

    public static List<UsuarioDto> convert(List<Usuario> usuarios) {
        return usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
    }

}
