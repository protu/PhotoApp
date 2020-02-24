package hr.dario.protulipac.photoapp.builder;

public abstract class UserBuilder {

    void setUsername(String username) {
    }

    void setPassword(String password) {
    }

    void setEnabled(Boolean enabled) {

    }

    public abstract Object getBuilder();
}
