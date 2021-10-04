package monkeys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.IOException;
@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(MonkeyRepository monkeyRepository, OrderRepository orderRepository, UserRepository userRepository, AttractionRepository attractionRepository) throws IOException {


        return args -> {
            userRepository.save(new User("natralalalia", "MiciCuMustar"));
            userRepository.findAll().forEach(user -> log.info("Preloaded " + user));
            attractionRepository.findAll().forEach(attraction -> log.info("Preloaded " + attraction));

        };
    }
}