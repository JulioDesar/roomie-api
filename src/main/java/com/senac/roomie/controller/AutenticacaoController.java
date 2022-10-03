package com.senac.roomie.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.senac.roomie.config.security.TokenService;
import com.senac.roomie.dto.LoginDto;
import com.senac.roomie.dto.TokenDto;
import com.senac.roomie.dto.UsuarioAllDto;
import com.senac.roomie.model.Usuario;
import com.senac.roomie.repository.UsuarioRepository;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UsuarioRepository bd;

	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginDto form) {

		UsernamePasswordAuthenticationToken dadosLogin = form.Convert();
		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);

			Integer id = tokenService.getIdUsuario(token);
			Optional<Usuario> user = bd.findById(id);
			UsuarioAllDto usuario = new UsuarioAllDto(user.get());

			if (user.isPresent()) {
				return ResponseEntity.ok(new TokenDto(token, "Bearer", usuario));
			}
			return ResponseEntity.notFound().build();

		} catch (BadCredentialsException e) {
			return ResponseEntity.badRequest().build();
		}

	}

	@GetMapping("/valid")
	public ResponseEntity<UsuarioAllDto> isTokenValid(@RequestParam String token) {
		boolean valid = tokenService.isTokenValid(token);

		if (valid) {
			Integer id = tokenService.getIdUsuario(token);
			Optional<Usuario> user = bd.findById(id);
			if (user.isPresent()) {
				return ResponseEntity.ok(new UsuarioAllDto(user.get()));
			}
		}
		return ResponseEntity.badRequest().build();

	}

}
