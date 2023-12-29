'use strict';

const usernamePage = document.querySelector('#username-page');
const chatPage = document.querySelector('#chat-page');
const usernameForm = document.querySelector('#usernameForm');
const messageForm = document.querySelector('#messageForm');
const messageInput = document.querySelector('#message');
const connectingElement = document.querySelector('.connecting');
const chatArea = document.querySelector('#chat-messages');
const logout = document.querySelector('#logout');

let stompClient = null;
let nickname = null;
let fullname = null;
let selectedUserId = null;

function connect(event) {
	console.log('connecting to server');
	const socket = new SockJS('/ws');
	stompClient = Stomp.over(socket);

	stompClient.connect({}, onConnected, onError); 
	event.preventDefault();

}

function onConnected() {
	console.log('connected');
	// Get form values
	const chatId = document.querySelector('input[name="chatId"]').value;
	const senderId = document.querySelector('input[name="senderId"]').value;
	const recipientId = document.querySelector('input[name="recipientId"]').value;
	const content = document.querySelector('input[name="content"]').value;
	const message = { 'chatId': `${chatId}`, 'senderId': `${senderId}`, 'recipientId': `${recipientId}`, 'content': `${content}` };

	stompClient.send(`/app/chats`,
		{},
		JSON.stringify(message)
	);

}

function onError() {
	console.log('connection error');
}

usernameForm.addEventListener('submit', connect, true); // step 1


