package com.projectManagementTool;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.mail.Message;

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private ProjectService projectService;
	
	@Override
	public Messages sendMessage(Long senderId, Long projectId, String content) throws Exception {
		// TODO Auto-generated method stub
		User sender = userRepository.findById(senderId)
				.orElseThrow(() -> new Exception("User not found with id : " + senderId));
		Chat chat = projectService.getProjectById(projectId).getChat();
		Messages message = new Messages();
		message.setContent(content);
		message.setSender(sender);
		message.setCreatedAt(LocalDateTime.now());
		message.setChat(chat);
		Messages savedMessage=messageRepository.save(message);
		chat.getMessages().add(savedMessage);
		
		return savedMessage;
	}

	@Override
	public List<Messages> getMessagesByProjectId(Long projectId) throws Exception {
		// TODO Auto-generated method stub
		Chat chat = projectService.getChatByProjectId(projectId);
		List<Messages> findByChatIdOrderByCreatedAtAsc = messageRepository.findByChatIdOrderByCreatedAtAsc(chat.getId());
		
		return findByChatIdOrderByCreatedAtAsc;
	}

}
