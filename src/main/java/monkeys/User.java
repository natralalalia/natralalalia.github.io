package monkeys;

import monkeys.Monkey;

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

    private ArrayList<Monkey> monkeysSeen;

    User() {}

    User(String username, ArrayList<Monkey> monkeysSeen){
        this.username = username;
        this.monkeysSeen = monkeysSeen;
    }

    public ArrayList<Monkey> getMonkeysSeen() {
        return monkeysSeen;
    }

    public void setMonkeysSeen(ArrayList<Monkey> monkeysSeen) {
        this.monkeysSeen = monkeysSeen;
    }

    private void userSawMonkey(){
        Monkey seenMonkey = new Monkey("Natalia", "Gutanu", "Chimpanzee");
        monkeysSeen.add(seenMonkey);
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

    @Override
    public boolean equals(Object u) {
        if (this == u)
            return true;
        if (!(u instanceof User))
            return false;
        User user = (User) u;
        return Objects.equals(this.id, user.id) && Objects.equals(user.monkeysSeen, this.monkeysSeen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.username, this.monkeysSeen);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + this.id + ", username='" + this.username + '\'' + ", monkeysSeen=" + this.monkeysSeen + '}';
    }
}
