package com.botq.gymsystem.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 15, message = "Please use from 3 up to 15 characters")
    @Column(updatable = false, unique = true)
    private String username;

    private String firstName;
    private String lastName;

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(updatable = false)
    private Date registrationDate;

    @ManyToMany(mappedBy = "userList")
    private List<Exercise> exerciseList = new ArrayList<>();

    //todo is date broken while adding new entry?
    @PrePersist
    public void onCreate(){
        this.registrationDate = new Date();
    }
}
