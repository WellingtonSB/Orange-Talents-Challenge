package com.wsb.OrangeTalentsChallenge.Controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wsb.OrangeTalentsChallenge.Model.Usuario;
import com.wsb.OrangeTalentsChallenge.Repository.UsuarioRepository;
import com.wsb.OrangeTalentsChallenge.Service.UsuarioService;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins ="*",allowedHeaders="*")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@Autowired
	private UsuarioRepository  repository;
	
	
	@GetMapping
	public ResponseEntity<List<Usuario>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}//retirar(apenas verificação se os usuarios foram cadastrados)
	
	
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
