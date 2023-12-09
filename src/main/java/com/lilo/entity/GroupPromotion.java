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
@Table(name = "group_promotion")
@Getter
@Setter
@NoArgsConstructor
public class GroupPromotion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "group_id")
	private int groupId;

	@Column(name = "promoter_user_id")
	private int promoterUserId;

	@Column(name = "promoted_user_id")
	private int promotedUserId;

	@Enumerated(EnumType.STRING)
	@Column(name = "promoted_role")
	private GroupRoles promotedRole;

	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Column(name = "promotion_timestamp")
	private LocalDateTime promotionTimestamp;

	public GroupPromotion(int groupId, int promoterUserId, int promotedUserId, GroupRoles promotedRole) {
		this.groupId = groupId;
		this.promoterUserId = promoterUserId;
		this.promotedUserId = promotedUserId;
		this.promotedRole = promotedRole;
		this.promotionTimestamp = LocalDateTime.now();
	}

}
