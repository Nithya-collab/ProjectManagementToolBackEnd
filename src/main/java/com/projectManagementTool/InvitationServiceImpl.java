package com.projectManagementTool;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvitationServiceImpl implements InvitationService{

	@Autowired
	private InvitationRepository invitationRespository;
	
	@Autowired
	private EmailService emailService;
	
	@Override
	public void sendInvitation(String email, Long projectId) {
		// TODO Auto-generated method stub
		
		String invitationToken = UUID.randomUUID().toString();
		Invitations invitation = new Invitations();
		invitation.setEmail(email);
		invitation.setProjectId(projectId);
		invitation.setToken(invitationToken);
		
		invitationRespository.save(invitation);
//		String invitationLink = "http://localhost:8080/api/projects/accept_invitation?token=" + invitationToken;
		String invitationLink="https://projectmanagementtoolfrontend-production-aa23.up.railway.app/accept_invitation?token="+invitationToken;
		try {
			emailService.sendEmailWithToken(email, invitationLink);
		}catch(Exception e) { System.out.print(e); }
	}

	@Override
	public Invitations acceptInvitation(String token, Long userId) throws Exception {
		// TODO Auto-generated method stub
		Invitations invitation=invitationRespository.findByToken(token);
		if(invitation == null) {
			throw new Exception("Invalid invitation");	
		}
		return invitation;
	}

	@Override
	public String getTokenByMail(String userEmail) {
		// TODO Auto-generated method stub
		Invitations invitation = invitationRespository.findByEmail(userEmail);
		return invitation.getToken();
	}

	@Override
	public void deleteToken(String token) {
		// TODO Auto-generated method stub
		Invitations invitation = invitationRespository.findByToken(token);
		invitationRespository.delete(invitation);
		
	}

}
