package com.projectManagementTool;

import java.util.List;

import javax.xml.stream.events.Comment;

public interface CommentService {

	Comments createComment(Long issueId , Long userId , String comment);
	void deleteComment(Long commentid , Long userId) throws Exception;
	List<Comments> findCommentByIssueId(Long issueId);
}
