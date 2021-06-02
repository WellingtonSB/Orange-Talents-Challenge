package com.wsb.OrangeTalentsChallenge.services;

import java.util.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wsb.OrangeTalentsChallenge.Model.Veiculo;


@FeignClient(name = "FIPE" ,url= "http://fipeapi.appspot.com/api/1/carros/")
public interface FipeService {

    @GetMapping("{marca}/{modelo}/{ano}.json")
    Veiculo buscaCarro (@PathVariable("marca") String marca,
    					@PathVariable("modelo") String modelo,
    					@PathVariable("anoModelo") String anoModelo);
}