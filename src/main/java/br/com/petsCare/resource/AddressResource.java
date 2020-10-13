package br.com.petsCare.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.petsCare.entities.City;
import br.com.petsCare.entities.State;
import br.com.petsCare.repository.CityRepository;
import br.com.petsCare.repository.StateRepository;

@RestController
@RequestMapping(value = "/address")
public class AddressResource {
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	
	@GetMapping
	@RequestMapping(value = "/states")
	public ResponseEntity<List<State>> findAllStates(){
		List<State> states = stateRepository.findAll();
		return ResponseEntity.ok().body(states);
	}
	
	@GetMapping
	@RequestMapping(value = "/citys")
	public ResponseEntity<List<City>> findAllCity(String initials){
		List<City> citys = cityRepository.findByState_Initials(initials);
		
		return ResponseEntity.ok().body(citys);
	}

}
