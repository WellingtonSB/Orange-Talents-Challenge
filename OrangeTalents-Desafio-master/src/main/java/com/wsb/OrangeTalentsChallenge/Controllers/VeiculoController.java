package com.wsb.OrangeTalentsChallenge.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wsb.OrangeTalentsChallenge.Model.Veiculo;
import com.wsb.OrangeTalentsChallenge.services.FipeService;

import com.wsb.OrangeTalentsChallenge.services.VeiculoService;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

	@Autowired
	private VeiculoService serviceVeiculo;

	@Autowired
	private FipeService fipeService;

	@Autowired
	private FipeController fipeController;

	@PostMapping("/marca/{marca}/modelo/{modelo}/ano/{anoModelo}")
	public ResponseEntity<?> post(@PathVariable("marca") String marca, @PathVariable("modelo") String modelo,
			@PathVariable("anoModelo") String anoModelo) {

		try {
			Veiculo veiculo = fipeController.getCar(marca, modelo, anoModelo).getBody();
			serviceVeiculo.cadastrarVeiculo(veiculo);
			return ResponseEntity.status(HttpStatus.CREATED).body("Usuario Cadastrardo");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao tentar localizar o veiculo");
		}

	}
}
