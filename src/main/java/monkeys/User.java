package monkeys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.*;

@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue
    private long id;

    private String username;

    private String hashedPassword;

    User() {}

    User(String username, String hashedPassword){
        this.username = username;
        this.hashedPassword = hashedPassword;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getHashedPassword() {
        return this.hashedPassword;
    }

    @Override
    public boolean equals(Object u) {
        if (this == u)
            return true;
        if (!(u instanceof User))
            return false;
        User user = (User) u;
        return Objects.equals(this.id, user.id) && Objects.equals(user.hashedPassword, this.hashedPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.username, this.hashedPassword);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + this.id + ", username='" + this.username + '}';
    }
}
