package com.daersh.daersh_project.refresh;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Entity
public class Refresh {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    private String userId;
    private String refresh;
    private String expiration;

}
