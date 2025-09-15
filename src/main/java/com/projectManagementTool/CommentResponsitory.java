package com.projectManagementTool;

import java.util.List;

import javax.xml.stream.events.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentResponsitory extends JpaRepository<Comments , Long> {
    List<Comments> findByIssueId(Long issueId);
}
