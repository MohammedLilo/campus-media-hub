package com.lilo.entity;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

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
@Table(name = "kicked_group_member")
@Getter
@Setter
@NoArgsConstructor
public class KickedGroupMember {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "group_id")
	private int groupId;

	@Column(name = "kick_timestamp")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime timestamp;

	public KickedGroupMember(int userId, int groupId) {
		super();
		this.userId = userId;
		this.groupId = groupId;
		this.timestamp = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return "KickedGroupMember [id=" + id + ", userId=" + userId + ", groupId=" + groupId + ", kickTimestamp="
				+ timestamp + "]";
	}

}
