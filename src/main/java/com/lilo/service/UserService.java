package com.lilo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.lilo.entity.User;
import com.lilo.entity.UserDetail;
import com.lilo.entity.UserPrivacy;
import com.lilo.enums.Genders;

public interface UserService {
	@Transactional
	public void save(User user);

	Page<User> findAll(int pageNumber, int pageSize, Sort sort);

	public User findById(int id);

	public User findByEmail(String email);

	public String findFullNameById(int id);

	public String findFirstNameById(int id);

	public String findLastNameById(int id);
//	public User findByEmailWithFriendships(String email);

//	public User findByEmailWithPosts(String email);

//	public User findByEmailWithComments(String email);
	@Transactional
	public void update(int id, User user);

	@Transactional
	void updateFirstName(User user, String newfirstName);

	@Transactional
	void updateLastName(User user, String newLastName);

	@Transactional
	void updatePassword(User user, String newPassword);

	@Transactional
	void updateGender(User user, Genders newGender);

	@Transactional
	void updateUserDetail(User user, UserDetail newUserDetail);

	@Transactional
	void updateUserPrivacy(User user, UserPrivacy newUserPrivacy);

	@Transactional
	public void deleteById(int id);

}
