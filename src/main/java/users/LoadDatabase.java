package users;

import monkeys.Monkey;
import org.springframework.context.annotation.Configuration;
import users.User;
import users.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(users.LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {

        HashSet<Monkey> monkeysSeen = new HashSet<Monkey>();
        Monkey monkeyOne = new Monkey("Sean", "Davis", "macaque");
        Monkey monkeyTwo = new Monkey("Helen", "Rogan", "macaque");
        monkeysSeen.add(monkeyOne);
        monkeysSeen.add(monkeyTwo);
        

        return args -> {
            log.info("Preloading " + repository.save(new User("Bogdan", monkeysSeen)));
            log.info("Preloading " + repository.save(new User("Freddie", monkeysSeen)));
        };
    }
}
