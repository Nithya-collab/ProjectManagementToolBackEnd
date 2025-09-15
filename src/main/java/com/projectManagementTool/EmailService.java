package com.projectManagementTool;

public interface EmailService {

	void sendEmailWithToken(String userEmail , String link) throws Exception ;
}
