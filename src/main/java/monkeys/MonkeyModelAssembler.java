package monkeys;


import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
class MonkeyModelAssembler implements RepresentationModelAssembler<Monkey, EntityModel<Monkey>>{

    @Override
    public EntityModel<Monkey> toModel(Monkey monkey) {
        return EntityModel.of(monkey, linkTo(methodOn(MonkeyController.class).one(monkey.getId())).withSelfRel(),
                linkTo(methodOn(MonkeyController.class).all()).withRel("monkeys"));
    }

    @Override
    public CollectionModel<EntityModel<Monkey>> toCollectionModel(Iterable<? extends Monkey> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
