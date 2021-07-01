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
    // this method is based on converting a non-model object (Monkey) to a model-based object (EntityModel<Monkey>)

    // linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel() asks that Spring HATEOAS build a link to
    // the EmployeeController 's one() method, and flag it as a self link.

    // linkTo(methodOn(EmployeeController.class).all()).withRel("employees") asks Spring HATEOAS to build a link to
    // the aggregate root, all(), and call it "employees"

    @Override
    public CollectionModel<EntityModel<Monkey>> toCollectionModel(Iterable<? extends Monkey> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
