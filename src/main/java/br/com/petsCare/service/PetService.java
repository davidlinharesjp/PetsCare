package br.com.petsCare.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.petsCare.entities.Pet;
import br.com.petsCare.repository.PetRepository;
import br.com.petsCare.service.exception.DatabaseException;
import br.com.petsCare.service.exception.ResourceNotFoundException;

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

	public Pet getOne(long id) {
		try {
			return petRepository.getOne(id);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	};

	public void delete(Long id) {
		try {
			petRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Pet update(Long id, Pet newPet) {
		try {
			Pet pet = petRepository.findByID(id);
			updateDate(pet, newPet);
			return petRepository.save(pet);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}

	/**
	 * @param pet - Pet Cadastrado na Base
	 * @param newPet - Pet a Alterar na Base
	 * 
	 * Recomenndações Faz a verificação se existe list 
	 * no novo cadastro senão remove geral
	 * 
	 * E faz a verificação se existi lista nos dados novos
	 * e faz a alteração de acordo com a existencia ou não 
	 * */
	private void updateDate(Pet pet, Pet newPet) {
		pet.setName(newPet.getName());
		pet.setBirthday(newPet.getBirthday());
		pet.setColor(newPet.getColor());
		pet.setDoctorName(newPet.getDoctorName());
		pet.updateRecomendation(newPet.getRecommendations());
		pet.setSex(newPet.getSex());
		pet.setUser(newPet.getUser());
		pet.setRaca(newPet.getRaca());
	}

	public Pet insert(Pet pet) {
		return petRepository.save(pet);
	}
}
