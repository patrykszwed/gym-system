package com.botq.gymsystem.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Exercise identifier is required")
    @Column(updatable = false, unique = true)
    private String exerciseId;
    @NotBlank(message = "Exercise name is required")
    private String exerciseName;
    private String description;

    @ManyToMany(cascade = {CascadeType.REFRESH})
    @JoinTable(name = "user_exercise",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> userList = new ArrayList<>();
}

//todo Is something like Backlog needed? For example for tracking series and repetitions each day.
