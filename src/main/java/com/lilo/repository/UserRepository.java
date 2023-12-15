package com.lilo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lilo.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {

	public Optional<User> findByEmail(String email);

	@Query("SELECT CONCAT(u.firstName, ' ', u.lastName) AS fullName FROM User u WHERE id= :id")
	public String findFullNameById(@Param("id") int id);

	@Query("SELECT u.firstName FROM User u WHERE id= :id")
	public String findFirstNameById(@Param("id") int id);

	@Query("SELECT u.lastName FROM User u WHERE id= :id")
	public String findLastNameById(@Param("id") int id);
//	@Query("SELECT u FROM User u LEFT JOIN FETCH u.friendships WHERE u.email = :email")
//	public User findByEmailWithFriendships(@Param("email") String email);
//
//	@Query("SELECT u FROM User u LEFT JOIN FETCH u.posts WHERE u.email= :email")
//	public User findByEmailWithPosts(@Param("email") String email);
//
//	@Query("SELECT u FROM User u LEFT JOIN FETCH u.comments WHERE u.email= :email")
//	public User findByEmailWithComments(@Param("email") String email);
}
