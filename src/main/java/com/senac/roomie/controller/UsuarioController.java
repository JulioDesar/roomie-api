package com.senac.roomie.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.senac.roomie.dto.UsuarioAllDto;
import com.senac.roomie.dto.UsuarioDadosDto;
import com.senac.roomie.dto.UsuarioDto;
import com.senac.roomie.dto.UsuarioUpdateDto;
import com.senac.roomie.model.Usuario;
import com.senac.roomie.repository.UsuarioRepository;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UsuarioController {

	@Autowired
	private UsuarioRepository bd;

	@GetMapping("/user")
	public Page<UsuarioDto> byName(@RequestParam(required = false) String nome,
			@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {

		if (nome == null) {
			Page<Usuario> lista = bd.findAll(paginacao);

			return UsuarioDto.convert(lista);
		}

		Page<Usuario> lista = bd.findByName(nome, paginacao);
		return UsuarioDto.convert(lista);

	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioAllDto> byId(@PathVariable Integer id) {
		Optional<Usuario> user = bd.findById(id);

		if (user.isPresent()) {
			return ResponseEntity.ok(new UsuarioAllDto(user.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping("/")
	public ResponseEntity<UsuarioDto> save(@Valid @RequestBody UsuarioDadosDto userForm,
			UriComponentsBuilder uriBuilder) {
		Usuario user = userForm.convert();
		bd.save(user);

		URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();

		return ResponseEntity.created(uri).body(new UsuarioDto(user));

	}

	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDto> update(@PathVariable Integer id,
			@RequestBody @Valid UsuarioUpdateDto newUserData) {
		Usuario user = newUserData.atualizar(id, bd);

		return ResponseEntity.ok(new UsuarioDto(user));
	}

}
