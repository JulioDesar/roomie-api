package com.senac.roomie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.senac.roomie.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	@Query("select c from usuario c where nome like %?1%")
	List<Usuario> findByName(String nome);
    
}
