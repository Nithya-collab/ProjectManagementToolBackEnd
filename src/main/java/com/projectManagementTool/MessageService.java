package com.projectManagementTool;

import java.util.List;

import jakarta.mail.Message;

public interface MessageService {

	Messages sendMessage(Long senderId , Long chatId , String content) throws Exception ;
	List<Messages> getMessagesByProjectId(Long projectId) throws Exception;
	
}
