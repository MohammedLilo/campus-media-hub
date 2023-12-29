package com.lilo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lilo.document.ChatMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChatMessagesDTO {

	private List<ChatMessage> chatMessages;

	@JsonProperty("isLast")
	private boolean isLast;
}
