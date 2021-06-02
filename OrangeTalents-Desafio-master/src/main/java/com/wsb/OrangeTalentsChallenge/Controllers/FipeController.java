package com.wsb.OrangeTalentsChallenge.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.wsb.OrangeTalentsChallenge.Model.Veiculo;
import com.wsb.OrangeTalentsChallenge.services.FipeService;

@RestController
public class FipeController {

	@Autowired
	private FipeService service;

    @GetMapping("{marca}/{modelo}/{ano}")
    public ResponseEntity<Veiculo> getCar(@PathVariable String marca,
                                          @PathVariable String modelo,
                                          @PathVariable String ano){
        Veiculo veiculo = service.buscaCarro(marca,modelo,ano);
        return ResponseEntity.ok().body(veiculo);
    }
}