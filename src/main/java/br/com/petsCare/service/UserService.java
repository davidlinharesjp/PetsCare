package br.com.petsCare.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.petsCare.entities.User;
import br.com.petsCare.entities.dto.UserDTO;
import br.com.petsCare.entities.form.UserRegisterForm;
import br.com.petsCare.repository.UserRepository;
import br.com.petsCare.service.exception.DatabaseException;
import br.com.petsCare.service.exception.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public Page<User> findAllPagination(Pageable pagination) {
		Page<User> pageUser = userRepository.findAll(pagination);
		return pageUser;
	}

	public Page<User> findAllPagination(Pageable pagination, String searchExpression) {
		return userRepository.findByNameContainsIgnoreCase(searchExpression, pagination);
	}

	public User findById(Long id) {
		Optional<User> opUser = userRepository.findById(id);
		return opUser.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public User insert(User user) {
		if(user.getKey_password() != null) {
			user.setKey_password(new BCryptPasswordEncoder().encode(user.getKey_password()));			
		}
		return userRepository.save(user);
	}

	public User getOne(long id) {
		try {
			return userRepository.getOne(id);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	};

	public void delete(Long id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public User update(Long id, User newUser) {
		try {
			User user = userRepository.getOne(id);
			updateDate(user, newUser);
			return userRepository.save(user);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateDate(User user, User newUser) {
		user.setName(newUser.getName());
		user.setEmail(newUser.getEmail());
		user.setPhone(newUser.getPhone());
		user.setEmail(newUser.getEmail());
		

	}

	public UserDTO register(UserRegisterForm userRegister) {
		User user = new User (userRegister);
		if(user.getKey_password() != null) {
			user.setKey_password(new BCryptPasswordEncoder().encode(user.getKey_password()));			
		}
		
		user = userRepository.save(user);
		return new UserDTO(user);
	}

}
