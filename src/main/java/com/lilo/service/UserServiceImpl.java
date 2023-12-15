package com.lilo.service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lilo.entity.User;
import com.lilo.entity.UserDetail;
import com.lilo.entity.UserPrivacy;
import com.lilo.enums.Genders;
import com.lilo.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void save(User user) {
		user.setRegistrationTimestamp(LocalDateTime.now());
		userRepository.save(user);
	}

	@Override
	public Page<User> findAll(int pageNumber, int pageSize, Sort sort) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		return userRepository.findAll(pageable);
	}

	@Override
	public User findById(int id) {
		Optional<User> foundUser = userRepository.findById(id);
		if (foundUser.isPresent())
			return foundUser.get();
		else
			throw new EntityNotFoundException("No matching user is found.");
	}

	@Override
	public User findByEmail(String email) {
		Optional<User> foundUser = userRepository.findByEmail(email);
		if (foundUser.isPresent())
			return userRepository.findByEmail(email).get();
		else
			throw new NoSuchElementException("no user found for the given email.");
	}

	@Override
	public String findFullNameById(int id) {
		String fullName = userRepository.findFullNameById(id);
		if (fullName != null)
			return fullName;
		else
			throw new EntityNotFoundException();
	}

	@Override
	public String findFirstNameById(int id) {
		return userRepository.findFirstNameById(id);
	}

	@Override
	public String findLastNameById(int id) {
		return userRepository.findLastNameById(id);
	}

//	@Override
//	public User findByEmailWithFriendships(String email) {
//		User user = userRepository.findByEmailWithFriendships(email);
//		return user;
//	}
//
//	@Override
//	public User findByEmailWithPosts(String email) {
//		User user = userRepository.findByEmailWithPosts(email);
//		return user;
//	}
//
//	@Override
//	public User findByEmailWithComments(String email) {
//		User user = userRepository.findByEmailWithComments(email);
//		return user;
//	}

	@Override
	public void update(int id, User user) {
		Optional<User> existingUserOptional = userRepository.findById(id);
		if (existingUserOptional.isPresent()) {
			User existingUser = existingUserOptional.get();
			user.setRegistrationTimestamp(existingUser.getRegistrationTimestamp());
			user.setUserDetail(existingUser.getUserDetail());
			user.setUserPrivacy(existingUser.getUserPrivacy());
			userRepository.save(user);
		} else
			throw new EntityNotFoundException("cannot update a non existing user.");
	}

	@Override
	public void updateFirstName(User user, String newfirstName) {
		user.setFirstName(newfirstName);
		userRepository.save(user);
	}

	@Override
	public void updateLastName(User user, String newLastName) {
		user.setLastName(newLastName);
		userRepository.save(user);

	}

	@Override
	public void updatePassword(User user, String newPassword) {
		user.setPassword(newPassword);
		userRepository.save(user);
	}

	@Override
	public void updateGender(User user, Genders newGender) {
		user.setGender(newGender);
		userRepository.save(user);
	}

	@Override
	public void updateUserDetail(User user, UserDetail newUserDetail) {
		user.setUserDetail(newUserDetail);
		userRepository.save(user);
	}

	@Override
	public void updateUserPrivacy(User user, UserPrivacy newUserPrivacy) {
		user.setUserPrivacy(newUserPrivacy);
		userRepository.save(user);
	}

	@Override
	public void deleteById(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}

}
