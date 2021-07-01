package monkeys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

// @SpringBootApplication is a meta-annotation that pulls in component scanning, autoconfiguration, and property support.

@ComponentScan
@EnableAutoConfiguration
//@Import(UsersApplication.class)
@SpringBootApplication
public class MonkeyApplication {

  public static void main(String... args) {
//      Object[] sources = {MonkeyApplication.class, UsersApplication.class};
    SpringApplication.run(MonkeyApplication.class, args);
  }
}


