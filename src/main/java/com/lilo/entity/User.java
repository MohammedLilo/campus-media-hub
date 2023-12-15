package com.lilo.entity;

import java.time.LocalDateTime;

import com.lilo.enums.Genders;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	private Genders gender;

	@Column(name = "registration_timestamp")
	private LocalDateTime registrationTimestamp;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_detail_id")
	private UserDetail userDetail;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_privacy_id")
	private UserPrivacy userPrivacy;

//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "user_id")
//	private List<Friendship> friendships;
//
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "user_id")
//	private List<Post> posts;
//
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "user_id")
//	private List<Comment> comments;
//
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "user_id")
//	private List<Block> blocks;
//
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "sender_id")
//	private List<Chat> chats;

	public User(String firstName, String lastName, String email, String password, Genders gender) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.registrationTimestamp = LocalDateTime.now();
	}

}
