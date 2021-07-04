//package monkeys;
//
//import monkeys.User;
//import monkeys.UserNotFoundException;
//import org.springframework.hateoas.CollectionModel;
//import org.springframework.hateoas.EntityModel;
//import org.springframework.web.bind.annotation.*;
//import monkeys.UserRepository;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
//
//@RestController
//public class UserController {
//    private final UserRepository repository;
//    private final UserModelAssembler assembler;
//
//    UserController(UserRepository repository, UserModelAssembler assembler) {
//            this.repository = repository;
//            this.assembler = assembler;
//        }
//
//    @GetMapping("/users")
//    CollectionModel<EntityModel<User>> all() {
//        List<EntityModel<User>> users = repository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
//        return CollectionModel.of(users, linkTo(methodOn(MonkeyController.class).all()).withSelfRel());
//    }
//    // end::get-aggregate-root[]
//
//    @PostMapping("/users")
//    User newUser(@RequestBody User newUser) {
//        return repository.save(newUser);
//    }
//
//    // Single item
//
//    @GetMapping("/users/{id}")
//    User one(@PathVariable Long id) {
//
//        return repository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException(id));
//    }
//
//}

package monkeys;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {

    private final UserRepository userRepository;
    private final UserModelAssembler assembler;

    UserController(UserRepository userRepository, UserModelAssembler assembler) {
        this.userRepository = userRepository;
        this.assembler = assembler;
    }

    @GetMapping("/users")
    CollectionModel<EntityModel<User>> all() {
        List<EntityModel<User>> users = userRepository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @GetMapping("/users/{id}")
    EntityModel<User> one(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return assembler.toModel(user);
    }

    @PostMapping("/users")
    ResponseEntity<EntityModel<User>> newUser(@RequestBody User user) {
        User newUser = userRepository.save(user);

        return ResponseEntity.created(linkTo(methodOn(UserController.class).one(newUser.getId())).toUri()).body(assembler.toModel(newUser));
    }

    @DeleteMapping("/users/{id}/cancel")
    ResponseEntity<?> cancel(@PathVariable Long id) {

        User user = userRepository.findById(id) //
                .orElseThrow(() -> new UserNotFoundException(id));

        return ResponseEntity //
                .status(HttpStatus.METHOD_NOT_ALLOWED) //
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
                .body(Problem.create() //
                        .withTitle("Method not allowed"));
    }

    @PutMapping("/users/{id}/complete")
    ResponseEntity<?> complete(@PathVariable Long id) {

        User user = userRepository.findById(id) //
                .orElseThrow(() -> new UserNotFoundException(id));

        return ResponseEntity //
                .status(HttpStatus.METHOD_NOT_ALLOWED) //
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
                .body(Problem.create() //
                        .withTitle("Method not allowed"));
    }
}
