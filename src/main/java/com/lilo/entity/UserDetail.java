package com.lilo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_detail")
@Getter
@Setter
@NoArgsConstructor
public class UserDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "university")
	private String university;

	@Column(name = "college")
	private String college;

	@Column(name = "department")
	private String department;

	@Column(name = "year_of_enrollment", columnDefinition = "YEAR")
	private Integer yearOfEnrollment;

	@Column(name = "year_of_graduation", columnDefinition = "YEAR")
	private Integer yearOfGraduation;

	@Column(name = "biography")
	private String biography;

	@Column(name = "profile_picture")
	private String profilePicture;

	public UserDetail(String university, String college, String department, Integer yearOfEnrollment,
			Integer yearOfGraduation, String biography) {
		super();
		this.university = university;
		this.college = college;
		this.department = department;
		this.yearOfEnrollment = yearOfEnrollment;
		this.yearOfGraduation = yearOfGraduation;
		this.biography = biography;
	}

}
