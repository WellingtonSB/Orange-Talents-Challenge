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

		switch (automovel.getAnoModelo().substring(automovel.getAnoModelo().length() - 1)) {
		case "0":
		case "1":
			return DayOfWeek.MONDAY.toString();
		case "2":
		case "3":
			return DayOfWeek.TUESDAY.toString();
		case "4":
		case "5":
			return DayOfWeek.WEDNESDAY.toString();
		case "6":
		case "7":
			return DayOfWeek.THURSDAY.toString();
		case "8":
		case "9":
			return DayOfWeek.FRIDAY.toString();
		default:
			return "";
		}
	}

	private boolean verificaRodizio(Veiculo veiculo) {

		if (LocalDateTime.now().getDayOfWeek().equals(DayOfWeek.SATURDAY)
				|| LocalDateTime.now().getDayOfWeek().equals(DayOfWeek.SUNDAY)
				|| LocalDateTime.now().getDayOfWeek().equals(DayOfWeek.valueOf(this.defineDiaRodizio(veiculo)))) {
			return true;
		}
		return false;
	}

	// chama as funções acima e salva o veiculo
	public void cadastrarVeiculo(Veiculo veiculo) {

		veiculo.setRodizioAtivo(verificaRodizio(veiculo));

		veiculo.setRodizio(defineDiaRodizio(veiculo));

		// return Optional.of(repository.save(veiculo));
		repository.save(veiculo);
	}

}
