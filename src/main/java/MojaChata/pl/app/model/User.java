package MojaChata.pl.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
    @SequenceGenerator(name = "userSeq", sequenceName = "USERS_SEQ", allocationSize = 1)
    private long id;

    // private String name;

    // private String surname;

    // private String email;

    @NotBlank(message = "username is mandatory")
    private String username;

    @Column(name = "password_hash")
    @NotBlank(message = "password is mandatory")
    private String passwordHash;

    User() {}

    User(String username, String passwordHash) {

    this.username = username;
    this.passwordHash = passwordHash;
    }

    public long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public void setPasswordHash(String newPasswordHash) {
        this.passwordHash = newPasswordHash;
    }


    @Override
    public String toString() {
        return "User{" + "id=" + this.id + ", username='" + this.username + '\'' + ", password='" + this.passwordHash + '}';
    }

}
