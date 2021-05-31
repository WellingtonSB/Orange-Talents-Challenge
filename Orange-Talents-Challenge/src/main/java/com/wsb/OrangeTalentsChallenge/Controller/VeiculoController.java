package com.wsb.OrangeTalentsChallenge.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wsb.OrangeTalentsChallenge.Model.Veiculos;
import com.wsb.OrangeTalentsChallenge.Repository.VeiculoRepository;

@RestController
@RequestMapping("/veiculo")
@CrossOrigin(origins ="*",allowedHeaders="*")
public class VeiculoController {
	
	@Autowired
	private VeiculoRepository repository;
	
	@PostMapping
	public ResponseEntity<Veiculos>post(@RequestBody Veiculos veiculo){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(veiculo));
	}
	
}

