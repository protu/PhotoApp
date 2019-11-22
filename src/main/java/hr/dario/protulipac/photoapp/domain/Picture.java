package hr.dario.protulipac.photoapp.domain;

import lombok.Data;

@Data
public class Picture {
    private long id;
    private String name;
    private String path;
    private String description;
    private String username;
}
