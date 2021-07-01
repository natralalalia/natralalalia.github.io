package users;

import users.User;
import users.UserNotFoundException;
import org.springframework.web.bind.annotation.*;
import users.UserRepository;

import java.util.List;

@RestController
public class UserController {


    private final UserRepository repository;

    UserController(UserRepository repository) {
            this.repository = repository;
        }

        @GetMapping("/users")
        public String getUsers(@RequestParam(value = "name", defaultValue = "World") String name) {
            return String.format("Hello %s!", name);
        }

    @GetMapping("/users")
    List<User> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    // Single item

    @GetMapping("/users/{id}")
    User one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

}
