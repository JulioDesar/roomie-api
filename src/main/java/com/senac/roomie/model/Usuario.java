package com.senac.roomie.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "usuario")
@Table(name = "usuario", uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
public class Usuario implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "CPF", unique = true)
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

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "senha")
	private String senha;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_perfis", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "id"))
	List<Perfil> perfis = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public long getCPF() {
		return CPF;
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
		this.senha = new BCryptPasswordEncoder().encode(senha);
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfis;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.ativo;
	}

}
