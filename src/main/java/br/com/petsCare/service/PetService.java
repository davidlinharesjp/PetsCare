package br.com.petsCare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.petsCare.entities.Pet;
import br.com.petsCare.repository.PetRepository;

@Service
public class PetService {

	@Autowired
	private PetRepository petRepository;

	public List<Pet> findAll() {
		return petRepository.findAll();
	}

	public Pet findById(Long id) {
		Optional<Pet> opPet = petRepository.findById(id);
		return opPet.get();

	}
}
