package com.wsb.OrangeTalentsChallenge.Controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wsb.OrangeTalentsChallenge.Model.Usuario;
import com.wsb.OrangeTalentsChallenge.repositories.UsuarioRepository;
import com.wsb.OrangeTalentsChallenge.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@Autowired
	private UsuarioRepository repository;

	@GetMapping
	public ResponseEntity<List<Usuario>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}// retirar(apenas verificação)

	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> post(@RequestBody Usuario usuario) {
		Optional<Usuario> user = service.cadastrarUsuario(usuario);
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(user.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
