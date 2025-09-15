package com.projectManagementTool;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Invitations,Long>{
 
	Invitations findByToken(String token);
	
	Invitations findByEmail(String userEmail);

}
