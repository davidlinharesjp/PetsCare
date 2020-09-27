package br.com.pestCare.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.pestCare.entities.User;
import br.com.pestCare.repository.UserRepository;
import br.com.pestCare.service.exception.DatabaseException;
import br.com.pestCare.service.exception.ResourceNotFoundException;

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

	public User insert(User obj) {
		return userRepository.save(obj);
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

	}

}
