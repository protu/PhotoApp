package hr.dario.protulipac.photoapp.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "pictures")
public class Picture {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "{validation.exercise.name.notEmpty}")
    private String name;

    private String path;

    @NotEmpty(message = "{validation.exercise.path.notEmpty}")
    private String description;

    @NotEmpty(message = "{validation.exercise.username.notEmpty}")
    private String username;
}
