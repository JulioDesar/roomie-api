package com.senac.roomie.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.senac.roomie.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	@Query("select c from usuario c where nome like %?1%")
	Page<Usuario> findByName(String nome, Pageable paginacao);
	
	Optional<Usuario> findById(Integer id);
	
	Optional<Usuario> findByEmail(String email);
    
}
