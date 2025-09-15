package com.projectManagementTool;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.mail.Message;

public interface MessageRepository extends JpaRepository<Messages,Long>{
	List<Messages>findByChatIdOrderByCreatedAtAsc(Long chatId) throws Exception;
}
