package users;

import monkeys.Monkey;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.HashSet;

@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;

    private String username;

    private HashSet<Monkey> monkeysSeen;

    User() {}

    User(String username, HashSet<Monkey> monkeysSeen){
        this.username = username;
        this.monkeysSeen = monkeysSeen;
    }

    private void userSawMonkey(){
        Monkey seenMonkey = new Monkey("Natalia", "Chimpanzee");
        monkeysSeen.add(seenMonkey);
    }
}
