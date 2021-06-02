package com.wsb.OrangeTalentsChallenge.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wsb.OrangeTalentsChallenge.Model.Veiculo;
import com.wsb.OrangeTalentsChallenge.services.UsuarioService;
import com.wsb.OrangeTalentsChallenge.services.VeiculoService;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

	@Autowired
	private VeiculoService service;
	
	@PostMapping()
	public ResponseEntity<Veiculo> post (@RequestBody Veiculo veiculo) {
		
	//return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarVeiculo(veiculo));

		Optional<Veiculo> veiculos = service.cadastrarVeiculo(veiculo);
		System.out.println("Objeto instaciado"+veiculos.get().getAnoModelo());
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(veiculos.get());
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	
}
