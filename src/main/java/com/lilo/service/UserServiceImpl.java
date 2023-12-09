package com.lilo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
		Page<User> page = userRepository.findAll(pageable);

		return page;
	}

	@Override
	public User findById(int id) {
		return userRepository.findById(id).get();
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public String findFullNameById(int id) {
		String fullName = userRepository.findFullNameById(id);
		return fullName;
	}

	@Override
	public String findFirstNameById(int id) {
		String firstName = userRepository.findFirstNameById(id);
		return firstName;
	}

	@Override
	public String findLastNameById(int id) {
		String lastName = userRepository.findLastNameById(id);
		return lastName;
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
		Optional<User> existingUser = userRepository.findById(id);
		if (existingUser.isPresent())
			userRepository.save(user);
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
	public void updateCountry(User user, String newCountry) {
		user.setCountry(newCountry);
		userRepository.save(user);
	}

	@Override
	public void updateCity(User user, String newCity) {
		user.setCity(newCity);
		userRepository.save(user);
	}

	@Override
	public void updatePhoneNumber(User user, String newPhoneNumber) {
		user.setPhoneNumber(newPhoneNumber);
		userRepository.save(user);
	}

	@Override
	public void updateBirthDate(User user, LocalDate newBirthDate) {
		user.setBirthDate(newBirthDate);
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
