package hr.dario.protulipac.photoapp.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
@SecondaryTables({@SecondaryTable(name = "authorities")
})
public class User {


    @Id
    private String username;
    private String password;
    private Boolean enabled;

    @Column(table = "authorities")
    private String authority;

}
