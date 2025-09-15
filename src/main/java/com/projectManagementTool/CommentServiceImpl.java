package com.projectManagementTool;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.xml.stream.events.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentResponsitory commentRepository;
	
	@Autowired
	private IssueRepository issueRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public CommentServiceImpl(CommentResponsitory commentRepository , IssueRepository issueRepository , UserRepository userRepository) {
		this.commentRepository = commentRepository;
		this.issueRepository = issueRepository;
		this.userRepository = userRepository;
	}
	
	@Override
	public Comments createComment(Long issueId, Long userId, String content) {
		// TODO Auto-generated method stub
		Optional<Issue> issueOptional = issueRepository.findById(issueId);
		Optional<User> userOptional = userRepository.findById(userId);
			
		Issue issue = issueOptional.get();
		User user = userOptional.get();
		
		Comments comment = new Comments();
		
		comment.setIssue(issue);
		comment.setUser(user);
	    comment.setCreatedDateTime(LocalDateTime.now());
	    comment.setContent(content);

	    Comments savedComment = commentRepository.save(comment);
	    issue.getComments().add(savedComment);
	    return savedComment;
	    
	}

	@Override
	public void deleteComment(Long commentId, Long userId) throws Exception {
		// TODO Auto-generated method stub
		Optional<Comments> commentOptional = commentRepository.findById(commentId);
		Optional<User> userOptional = userRepository.findById(userId);
		
		if(commentOptional.isEmpty()) {
			 throw new Exception("comment not found with id"+commentId);
		}
		if(userOptional.isEmpty()) {
			throw new Exception("user not found with id" + userId);
		}
		
		Comments comment = commentOptional.get();
		User user = userOptional.get();
		
		if(comment.getUser().equals(user)) {
			commentRepository.delete(comment);
		}else {
			throw new Exception("User does not have permission to delete this comments !");
		}
		
	}

	@Override
	public List<Comments> findCommentByIssueId(Long issueId)  {
		// TODO Auto-generated method stub
		return commentRepository.findByIssueId(issueId);
	}

}
