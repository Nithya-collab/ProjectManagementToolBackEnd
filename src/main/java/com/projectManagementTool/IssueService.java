package com.projectManagementTool;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

public interface IssueService {

	Issue getIssueById(Long issueId) throws Exception;
	List<Issue> getIssueByProjectId(Long projectId) throws Exception;
	Issue createIssue(IssueRequest issue  , User userid) throws Exception;
	void deleteIssue(Long issueId , Long userid) throws Exception;
	Issue addUserToIssue(Long issueId , Long userId ) throws Exception;
	Issue updateStatus(Long issueId , String status) throws Exception;
}
