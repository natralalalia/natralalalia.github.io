package monkeys;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Attr;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class AttractionController {

    private final AttractionRepository repository;

    private final AttractionModelAssembler assembler;

    AttractionController(AttractionRepository repository, AttractionModelAssembler assembler) {

        this.repository = repository;
        this.assembler = assembler;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/attractions")
    CollectionModel<EntityModel<Attraction>> all() {
        List<EntityModel<Attraction>> attractions = repository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(attractions, linkTo(methodOn(AttractionController.class).all()).withSelfRel());
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/attractions")
    ResponseEntity<?> newAttraction(@RequestBody Attraction newAttraction) {
        EntityModel<Attraction> entityModel = assembler.toModel(repository.save(newAttraction));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    // EntityModel<T> is a generic container from Spring HATEOAS that includes not only the data but a collection of links
    @GetMapping("/attractions/{id}")
    EntityModel<Attraction> one(@PathVariable Long id) {

        Attraction attraction = repository.findById(id) //
                .orElseThrow(() -> new AttractionNotFoundException(id));

        return assembler.toModel(attraction);
    }

    @PutMapping("/attractions/{id}")
    ResponseEntity<?> replaceAttraction(@RequestBody Attraction newAttraction, @PathVariable Long id) {
        Attraction updatedAttraction = repository.findById(id)
                .map(attraction -> {
                    attraction.setName(newAttraction.getName());
                    attraction.setAddress(newAttraction.getAddress());
                    attraction.setDescription(newAttraction.getDescription());
                    return repository.save(attraction);
                })
                .orElseGet(() -> {
                    newAttraction.setId(id);
                    return repository.save(newAttraction);
                });

        EntityModel<Attraction> entityModel = assembler.toModel(updatedAttraction);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @CrossOrigin(origins = "*")
    @PatchMapping("/attractions/{id}")
    ResponseEntity<?> updateAttraction(@RequestParam String fieldToUpdate, @RequestParam String newValue, @PathVariable Long id) {
        Attraction updatedAttraction = repository.findById(id)
                .map(attraction -> {
                    switch (fieldToUpdate) {
                        case "name":
                            attraction.setName(newValue);
                            break;
                        case "address":
                            attraction.setAddress(newValue);
                            break;
                        case "description":
                            attraction.setDescription(newValue);
                            break;
                    }
                    return repository.save(attraction);
                })
                .orElseGet(() -> {
                    return null;
                });

        assert updatedAttraction != null;
        EntityModel<Attraction> entityModel = assembler.toModel(updatedAttraction);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping("/attractions/{id}")
    ResponseEntity<?> deleteAttraction(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}