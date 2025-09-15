package com.projectManagementTool;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueServiceImpl implements IssueService{

	@Autowired
	private IssueRepository issueRepository;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Override
	public Issue getIssueById(Long issueId) throws Exception {
		// TODO Auto-generated method stub
		Optional<Issue> issue = issueRepository.findById(issueId);
		if(issue.isPresent()) {
			return issue.get();
		}
		throw new Exception ("No issue found with issueid "+ issueId);
	}

	@Override
	public List<Issue> getIssueByProjectId(Long projectId) throws Exception {
		// TODO Auto-generated method stub
		List<Issue> issues = issueRepository.findByProject_Id(projectId);
		if(!issues.isEmpty()) return issues;
		return issueRepository.findByProject_Id(projectId);
//		throw new Exception("No issue found with projectId "+projectId);
	}

	@Override
	public Issue createIssue(IssueRequest issueRequest, User user) throws Exception {
	    // Get project from projectId
	    Project project = projectService.getProjectById(issueRequest.getProjectId());

	    // Create new issue
	    Issue issue = new Issue();
	    issue.setTitle(issueRequest.getTitle());
	    issue.setDescription(issueRequest.getDescription());
	    issue.setStatus(issueRequest.getStatus());
	    issue.setPriority(issueRequest.getPriority());
	    issue.setDueDate(issueRequest.getDueDate());
	    issue.setProject(project);
	    
	    // Save to DB
	    return issueRepository.save(issue);
	}

//	public Issue createIssue(IssueRequest issueRequest, User userId) throws Exception {
//		// TODO Auto-generated method stub
//		Project project = projectService.getProjectById(issueRequest.getProjectId());
//		Issue issue = new Issue();
//		issue.setTitle(issueRequest.getTitle());
//		issue.setDescription(issueRequest.getDescription());
//		issue.setStatus(issueRequest.getStatus());
////		issue.setProjectId(issueRequest.getProjectId());
//		issue.setProject(project);
//		issue.setPriority(issueRequest.getPriority());
//		issue.setDueDate(issueRequest.getDueDate());
//		issue.setProject(project);
//		
//		
//		return issueRepository.save(issue);
//	}

	@Override
	public void deleteIssue(Long issueId, Long userId) throws Exception {
		// TODO Auto-generated method stub
		getIssueById(issueId);
		issueRepository.deleteById(issueId);
	}

	@Override
	public Issue addUserToIssue(Long issueId, Long userId) throws Exception {
		// TODO Auto-generated method stub
		User user = userService.findUserById(userId);
		Issue issue = getIssueById(issueId);
		issue.setAssignee(user);
		return issueRepository.save(issue);
	}

	@Override
	public Issue updateStatus(Long issueId, String status) throws Exception {
		// TODO Auto-generated method stub
		Issue issue = getIssueById(issueId);
		issue.setStatus(status);
		return issueRepository.save(issue);
	}

}
