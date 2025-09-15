package com.projectManagementTool;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	private String fullName;
	private String email;
	private String password;
	private int projectSize;
	
	@OneToMany(mappedBy="assignee" , cascade = CascadeType.ALL)
	@JsonIgnore
	List<Issue> assignedIssue = new ArrayList<>();
}
