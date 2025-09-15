package com.projectManagementTool;

public interface InvitationService {

	public void sendInvitation(String email , Long projectId);
	public Invitations acceptInvitation(String token , Long userId) throws Exception;
	public String getTokenByMail(String userEmail);
	void deleteToken(String token);
	
}
