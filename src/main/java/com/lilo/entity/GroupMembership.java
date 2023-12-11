package com.lilo.entity;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.lilo.enums.GroupRoles;

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
@Table(name = "group_membership")
@Getter
@Setter
@NoArgsConstructor
public class GroupMembership {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "group_id")
	private int groupId;

	@Column(name = "timestamp")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime timestamp;

	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private GroupRoles role;

	public GroupMembership(int userId, int groupId) {
		this.userId = userId;
		this.groupId = groupId;


	}

	@Override
	public String toString() {
		return "GroupMembership [id=" + id + ", userId=" + userId + ", groupId=" + groupId + ", membershipTimestamp="
				+ timestamp + ", role=" + role + "]";
	}
}
