package com.lilo.entity;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.lilo.enums.GroupPrivacyLevels;

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
@Table(name = "group_table")
@Getter
@Setter
@NoArgsConstructor
public class Group {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "timestamp")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime timestamp;

	@Enumerated(EnumType.STRING)
	@Column(name = "privacy_level")
	private GroupPrivacyLevels privacyLevel;

	@Column(name = "picture")
	private String picture;
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "group_id")
//	private List<GroupMembership> groupMemberships;
//
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "group_id")
//	private List<GroupPost> posts;
//
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "group_id")
//	private List<GroupJoinRequest> groupJoinRequests;

	public Group(String name, String description, GroupPrivacyLevels privacyLevel) {
		this.name = name;
		this.description = description;
		this.privacyLevel = privacyLevel;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", description=" + description + ", creationTimestamp="
				+ timestamp + ", privacyLevel=" + privacyLevel + "]";
	}

}
