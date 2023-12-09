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
@Table(name = "block")
@Getter
@Setter
@NoArgsConstructor
public class Block {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "blocked_user_id")
	private int blockedUserId;

	@Column(name = "block_timestamp")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime blockTimestamp;

	public Block(int userId, int blockedUserId) {
		super();
		this.userId = userId;
		this.blockedUserId = blockedUserId;
		this.blockTimestamp = LocalDateTime.now();
	}

}
