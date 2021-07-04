package monkeys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(MonkeyRepository monkeyRepository, OrderRepository orderRepository, UserRepository userRepository) {

        ArrayList<Monkey> monkeysSeen = new ArrayList<Monkey>();
        Monkey monkeyOne = new Monkey("Sean", "Davis", "macaque");
        Monkey monkeyTwo = new Monkey("Helen", "Rogan", "gorilla");
        monkeysSeen.add(monkeyOne);
        monkeysSeen.add(monkeyTwo);

        return args -> {
            monkeyRepository.save(new Monkey("Bogdan", "Bogdanovici", "macaque"));
            monkeyRepository.save(new Monkey("Freddie", "Mercury", "chimpanzee"));
            monkeyRepository.findAll().forEach(monkey -> log.info("Preloaded " + monkey));

            orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
            orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));
            orderRepository.findAll().forEach(order -> log.info("Preloaded " + order));

            userRepository.save(new User("natralalalia", monkeysSeen));
            userRepository.findAll().forEach(user -> log.info("Preloaded " + user));
        };
    }
}