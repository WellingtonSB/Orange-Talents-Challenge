package com.wsb.OrangeTalentsChallenge.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wsb.OrangeTalentsChallenge.Model.Veiculos;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculos, Long> {
	
}
