package com.wsb.OrangeTalentsChallenge.services;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wsb.OrangeTalentsChallenge.Model.Veiculo;
import com.wsb.OrangeTalentsChallenge.repositories.VeiculoRepository;

@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository repository;

	// define o dia do rodizio de acordo com o ultimo digito
	private String defineDiaRodizio(Veiculo automovel) {

		//Veiculo automovel = new Veiculo();

		switch (automovel.getAnoModelo().substring(automovel.getAnoModelo().length() - 1)) {
		case "0":
		case "1":
			System.out.println("Chamou rodizio SEGUNDA");
			return DayOfWeek.MONDAY.toString();
		case "2":
		case "3":
			System.out.println("Chamou rodizio TERÇA");
			return DayOfWeek.TUESDAY.toString();
		case "4":
		case "5":
			System.out.println("Chamou rodizio QUARTA");
			return DayOfWeek.WEDNESDAY.toString();
		case "6":
		case "7":
			System.out.println("Chamou rodizio QUINTA");
			return DayOfWeek.THURSDAY.toString();
		case "8":
		case "9":
			System.out.println("Chamou rodizio SEXTA");
			return DayOfWeek.FRIDAY.toString();
		default:
			return "";
		}
	}

	private boolean verificaRodizio(Veiculo veiculo) {
		
		if (LocalDateTime.now().getDayOfWeek().equals(DayOfWeek.SATURDAY)
				|| LocalDateTime.now().getDayOfWeek().equals(DayOfWeek.SUNDAY)||
				 LocalDateTime.now().getDayOfWeek().equals(DayOfWeek.valueOf(this.defineDiaRodizio(veiculo)))) {
			System.out.println("verifica rodizio true");
			return true;
			
		}
		System.out.println("verifica rodizio false");
		return false;
	}

	public Optional<Veiculo> cadastrarVeiculo(Veiculo veiculo) {

		//veiculo.setAnoModelo(veiculo.getAnoModelo());
	
		
		veiculo.setRodizioAtivo(verificaRodizio(veiculo));
		
		veiculo.setRodizio(defineDiaRodizio(veiculo));

		return Optional.of(repository.save(veiculo));
	}

}
