package monkeys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(MonkeyRepository monkeyRepository, OrderRepository orderRepository, UserRepository userRepository, AttractionRepository attractionRepository) throws IOException {

        ArrayList<Monkey> monkeysSeen = new ArrayList<Monkey>();
        Monkey monkeyOne = new Monkey("Sean", "Davis", "macaque");
        Monkey monkeyTwo = new Monkey("Helen", "Rogan", "gorilla");
        monkeysSeen.add(monkeyOne);
        monkeysSeen.add(monkeyTwo);

        String outString = new String(Files.readAllBytes(Paths.get("src/main/java/monkeys/fisa_1.txt")));


        return args -> {
            monkeyRepository.save(new Monkey("Bogdan", "Bogdanovici", "macaque"));
            monkeyRepository.save(new Monkey("Freddie", "Mercury", "chimpanzee"));
            monkeyRepository.findAll().forEach(monkey -> log.info("Preloaded " + monkey));

            orderRepository.save(new Order("Bustul lui Alexandru Odobescu", outString, Status.COMPLETED));
            orderRepository.save(new Order("Catedrala Mantuirii Neamului", "Catedrala Mantuirii Neamului este cea mai mare biserica ortodoxa din lume.", Status.IN_PROGRESS));
            orderRepository.findAll().forEach(order -> log.info("Preloaded " + order));

            userRepository.save(new User("natralalalia", monkeysSeen));
            userRepository.findAll().forEach(user -> log.info("Preloaded " + user));

            attractionRepository.save(new Attraction("CMN", "cea mai mare", "izvor"));
            attractionRepository.findAll().forEach(attraction -> log.info("Preloaded " + attraction));

        };
    }
}