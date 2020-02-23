package hr.dario.protulipac.photoapp.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Data
@Entity
@Table(name = "pictures")
public class Picture implements PictureInt, Cloneable{

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

    @Override
    public String process() {
        return "original";
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        }catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
