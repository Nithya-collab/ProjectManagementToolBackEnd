package com.projectManagementTool;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Comments {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private String content;
    
    private LocalDateTime createdDateTime;
    
    @ManyToOne
    private User user;
    
    @ManyToOne
    private Issue issue;
         
}
