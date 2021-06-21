package monkeys;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class MonkeyController {

  private final MonkeyRepository repository;

  MonkeyController(MonkeyRepository repository) {
    this.repository = repository;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/monkeys")
  List<Monkey> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/monkeys")
  Monkey newMonkey(@RequestBody Monkey newMonkey) {
    return repository.save(newMonkey);
  }

  // Single item
  
  @GetMapping("/monkeys/{id}")
  Monkey one(@PathVariable Long id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new MonkeyNotFoundException(id));
  }

  @GetMapping("/monkeys/{id}")
  EntityModel<Monkey> one(@PathVariable Long id) {

  Monkey monkey = repository.findById(id) //
      .orElseThrow(() -> new MonkeyNotFoundException(id));

  return EntityModel.of(monkey, //
      linkTo(methodOn(MonkeyController.class).one(id)).withSelfRel(),
      linkTo(methodOn(MonkeyController.class).all()).withRel("employees"));
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