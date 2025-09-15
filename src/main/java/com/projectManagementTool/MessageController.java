package com.projectManagementTool;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

	@Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;
    
    @PostMapping("/send")
    public ResponseEntity<Messages> sendMessage(@RequestBody CreateMessageRequest request)
      throws Exception {
    	User user = userService.findUserById(request.getSenderId());
//    	if(user == null)  new Exception("user not found with id " + request.getSenderId());
    	Chat chats = projectService.getProjectById(request.getProjectId()).getChat();
    	if(chats == null) throw new Exception("Chats not found");
    	Messages sentMessage = messageService.sendMessage(request.getSenderId(),
    			  request.getProjectId(), request.getContent());
     
    	return ResponseEntity.ok(sentMessage);
    }
    
    @GetMapping("/chat/{projectId}")
    public ResponseEntity<List<Messages>> getMessagesByChatId(@PathVariable Long projectId)
       throws Exception{
    	List<Messages> message = messageService.getMessagesByProjectId(projectId);
    	return ResponseEntity.ok(message);
    }
}
