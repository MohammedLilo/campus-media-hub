package com.lilo.entity;

import com.lilo.enums.Visibility;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_privacy")
@Getter
@Setter
@NoArgsConstructor
public class UserPrivacy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Enumerated(EnumType.STRING)
	@Column(name = "profile_picture_visibility")
	private Visibility profilePictureVisibility;

	@Enumerated(EnumType.STRING)
	@Column(name = "phone_number_visibility")
	private Visibility phoneNumberVisibility;

	@Enumerated(EnumType.STRING)
	@Column(name = "email_visibility")
	private Visibility emailVisibility;

	public UserPrivacy(Visibility profilePictureVisibility, Visibility phoneNumberVisibility,
			Visibility emailVisibility) {

		if (profilePictureVisibility == null)
			this.profilePictureVisibility = Visibility.ALL;
		else
			this.profilePictureVisibility = profilePictureVisibility;

		if (phoneNumberVisibility == null)
			this.phoneNumberVisibility = Visibility.FRIENDS;
		else
			this.phoneNumberVisibility = phoneNumberVisibility;

		if (emailVisibility == null)
			this.emailVisibility = Visibility.FRIENDS;
		else
			this.emailVisibility = emailVisibility;
	}
}
