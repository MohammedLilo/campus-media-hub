package com.lilo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lilo.document.Chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChatsDTO {
	private List<Chat> chats;
	@JsonProperty("isLast")
	private boolean isLast;
}
