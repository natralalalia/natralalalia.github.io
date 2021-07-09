package monkeys;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class MonkeyController {

  private final MonkeyRepository repository;

  private final MonkeyModelAssembler assembler;

  MonkeyController(MonkeyRepository repository, MonkeyModelAssembler assembler) {

    this.repository = repository;
    this.assembler = assembler;
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/hello")
  public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
    return String.format("Hello %s!", name);
  }

  @GetMapping("/monkeys")
  CollectionModel<EntityModel<Monkey>> all() {
    List<EntityModel<Monkey>> monkeys = repository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
    return CollectionModel.of(monkeys, linkTo(methodOn(MonkeyController.class).all()).withSelfRel());
  }

  @CrossOrigin(origins = "*")
  @PostMapping("/monkeys")
  ResponseEntity<?> newMonkey(@RequestBody Monkey newMonkey) {
    EntityModel<Monkey> entityModel = assembler.toModel(repository.save(newMonkey));

    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
  }
  // - The new Monkey object is saved as before, but the resulting object is wrapped using the MonkeyModelAssembler
  // - Spring MVC's ResponseEntity is used to create an HTTP 201 Created status message. This type of response
  // typically includes a Location response header, and we use the URI derived from the model's self-related link
  // - Additionally, return the model-based version of the saved object


  // EntityModel<T> is a generic container from Spring HATEOAS that includes not only the data but a collection of links
  @GetMapping("/monkeys/{id}")
  EntityModel<Monkey> one(@PathVariable Long id) {

    Monkey monkey = repository.findById(id) //
            .orElseThrow(() -> new MonkeyNotFoundException(id));

    return assembler.toModel(monkey);
  }

  @PutMapping("/monkeys/{id}")
  ResponseEntity<?> replaceMonkey(@RequestBody Monkey newMonkey, @PathVariable Long id) {
    Monkey updatedMonkey = repository.findById(id)
      .map(monkey -> {
        monkey.setName(newMonkey.getName());
        monkey.setSpecies(newMonkey.getSpecies());
        return repository.save(monkey);
      })
      .orElseGet(() -> {
        newMonkey.setId(id);
        return repository.save(newMonkey);
      });

    EntityModel<Monkey> entityModel = assembler.toModel(updatedMonkey);

    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
  }
  // the Monkey object built from the save() operation is then wrapped using the MonkeyModelAssembler into an
  // EntityModel<Monkey> object. Using the getRequiredLink() method, you can retrieve the Link created by the
  // MonkeyModelAssembler with a SELF rel. This method returns a Link which must be turned into a URI with the
  // toUri method.

  // Since we want a more detailed HTTP response code than 200 OK, we will use Spring MVC's ResponseEntity
  // wrapper. It has a handy static method created() where we can plug in the resource's URI. It's debatable
  // if HTTP 201 Created carries the right semantics since we aren't necessarily "creating" a new resource.
  // But it comes pre-loaded with a Location response header, so run with it.

  @DeleteMapping("/monkeys/{id}")
  ResponseEntity<?> deleteMonkey(@PathVariable Long id) {
    repository.deleteById(id);

    return ResponseEntity.noContent().build();
  }
}