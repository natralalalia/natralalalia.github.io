package monkeys;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class MonkeyController {

  private final MonkeyRepository repository;

  MonkeyController(MonkeyRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/hello")
  public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
    return String.format("Hello %s!", name);
  }

  @GetMapping("/monkeys")
  CollectionModel<EntityModel<Monkey>> all() {
    List<EntityModel<Monkey>> monkeys = repository.findAll().stream().map(monkey -> EntityModel.of(monkey,
            linkTo(methodOn(MonkeyController.class).one(monkey.getId())).withSelfRel(),
                    linkTo(methodOn(MonkeyController.class).all()).withRel("monkeys"))).collect(Collectors.toList());
    return CollectionModel.of(monkeys, linkTo(methodOn(MonkeyController.class).all()).withSelfRel());
  }

  // CollectionModel<> is another Spring HATEOAS container; it's aimed at encapsulating collections of
  // resources - instead of a single resource entity, like EntityModel<> from earlier.
  // CollectionModel<> too lets you include links.

  // Don't let that first statement slip by. What does "encapsulating collections" mean?
  // Collection of Monkeys?
  // Not quite.
  // Since we are talking REST, it should encapsulate collections of MONKEY RESOURCES
  // That's why you fetch all the monkeys, but then transform them into a list of EntityModel<Monkey> objects

  // WHAT IS THE POINT OF ADDING ALL THESE LINKS?
  // It makes it possible to evolve REST services over time. Existing links can be maintained while new
  // links can be added in the future. Newer clients may take advantage of the new links, while legacy
  // clients can sustain themselves on the old links. This is especially helpful if services get relocated
  // and moved around. As long as the link structure is maintained, clients can STILL find and interact with things

  @PostMapping("/monkeys")
  Monkey newMonkey(@RequestBody Monkey newMonkey) {
    return repository.save(newMonkey);
  }

  // EntityModel<T> is a generic container from Spring HATEOAS that includes not only the data but a collection of links
  @GetMapping("/monkeys/{id}")
  EntityModel<Monkey> one(@PathVariable Long id) {

    Monkey monkey = repository.findById(id) //
            .orElseThrow(() -> new MonkeyNotFoundException(id));

    return EntityModel.of(monkey,
            linkTo(methodOn(MonkeyController.class).one(id)).withSelfRel(),
            linkTo(methodOn(MonkeyController.class).all()).withRel("monkeys"));
    // linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel() asks that Spring HATEOAS build a link to
    // the EmployeeController 's one() method, and flag it as a self link.

    // linkTo(methodOn(EmployeeController.class).all()).withRel("employees") asks Spring HATEOAS to build a link to
    // the aggregate root, all(), and call it "employees"
  }

  @PutMapping("/monkeys/{id}")
  Monkey replaceMonkey(@RequestBody Monkey newMonkey, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(monkey -> {
        monkey.setName(newMonkey.getName());
        monkey.setSpecies(newMonkey.getSpecies());
        return repository.save(monkey);
      })
      .orElseGet(() -> {
        newMonkey.setId(id);
        return repository.save(newMonkey);
      });
  }

  @DeleteMapping("/monkeys/{id}")
  void deleteMonkey(@PathVariable Long id) {
    repository.deleteById(id);
  }
}