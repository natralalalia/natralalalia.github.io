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
  @GetMapping("/Monkeys")
  List<Monkey> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/Monkeys")
  Monkey newMonkey(@RequestBody Monkey newMonkey) {
    return repository.save(newMonkey);
  }

  // Single item
  
  @GetMapping("/Monkeys/{id}")
  Monkey one(@PathVariable Long id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new MonkeyNotFoundException(id));
  }

  @PutMapping("/Monkeys/{id}")
  Monkey replaceMonkey(@RequestBody Monkey newMonkey, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(Monkey -> {
        Monkey.setName(newMonkey.getName());
        Monkey.setRole(newMonkey.getRole());
        return repository.save(Monkey);
      })
      .orElseGet(() -> {
        newMonkey.setId(id);
        return repository.save(newMonkey);
      });
  }

  @DeleteMapping("/Monkeys/{id}")
  void deleteMonkey(@PathVariable Long id) {
    repository.deleteById(id);
  }
}