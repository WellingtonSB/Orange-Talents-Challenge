package com.wsb.OrangeTalentsChallenge.Service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wsb.OrangeTalentsChallenge.Model.Usuario;
import com.wsb.OrangeTalentsChallenge.Repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public Optional<Usuario> cadastrarUsuario (Usuario usuario){
		if(repository.findByCpf(usuario.getCpf()).isPresent() || repository.findByEmail(usuario.getEmail()).isPresent()) {
			return null;
		}
		return Optional.of(repository.save(usuario));
	}

}
