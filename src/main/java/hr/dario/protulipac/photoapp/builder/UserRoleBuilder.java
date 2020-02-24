package hr.dario.protulipac.photoapp.builder;

import hr.dario.protulipac.photoapp.domain.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserRoleBuilder extends UserBuilder {

    private String username;
    private String password;
    private Boolean enabled;

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public User getBuilder() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEnabled(enabled);
        user.setAuthority("ROLE_USER");
        return user;
    }
}
