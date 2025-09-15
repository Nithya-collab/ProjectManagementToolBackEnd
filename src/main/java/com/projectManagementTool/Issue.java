package com.projectManagementTool;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
//
//@Entity
//@Data
//public class Issue {
//
//	 @Id
//	 private Long id;
//	 
//	 private String title;
//	 private String description;
//	 private String status;
////	 private Long projectId;
//	 private String priority;
//	 private LocalDate dueDate;
//	 private List<String> tags = new ArrayList<>();
//	 
//	 @ManyToOne
//	 private User assignee;
//	 
//	 @ManyToOne
//	 private Project project;
//	 
//	 @JsonIgnore
//	 @OneToMany(mappedBy="issue",cascade = CascadeType.ALL,orphanRemoval = true)
//	 private List<Comments> comments = new ArrayList<>();
//}



@Entity
@Data
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String status;

    // Owns the foreign key
//    private Long projectId;

    private String priority;
    private LocalDate dueDate;

    @ElementCollection
    private List<String> tags = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "projectId")
    @JsonIgnore
    private Project project;

    @ManyToOne
    private User assignee;

    @JsonIgnore
    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comments> comments = new ArrayList<>();
}
