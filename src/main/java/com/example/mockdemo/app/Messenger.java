package com.example.mockdemo.app;

import com.example.mockdemo.messenger.MalformedRecipientException;
import com.example.mockdemo.messenger.MessageService;
import com.example.mockdemo.messenger.SendingStatus;
import com.example.mockdemo.messenger.ConnectionStatus;

public class Messenger {

	private MessageService ms;

	public Messenger(MessageService ms) {
		this.ms = ms;
	}

	public Messenger() {
		// TODO Auto-generated constructor stub
	}

	public int testConnection(String server) {
		if(ms.checkConnection(server) == ConnectionStatus.SUCCESS) {
			return 0;
		}else return 1;
	}

	public int sendMessage(String server, String message) {
		try{
			SendingStatus Status = ms.send(server, message);
			if(Status == SendingStatus.SENT) {
				return 0;
			}else return 1;
		}catch(MalformedRecipientException e){
			return 2;
		}
	}
}
