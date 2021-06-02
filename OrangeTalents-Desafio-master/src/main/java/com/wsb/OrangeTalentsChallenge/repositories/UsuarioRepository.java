package com.wsb.OrangeTalentsChallenge.repositories;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wsb.OrangeTalentsChallenge.Model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	public Optional<Usuario>findByCpf(String cpf);
	public Optional<Usuario>findByEmail(String email);
}



