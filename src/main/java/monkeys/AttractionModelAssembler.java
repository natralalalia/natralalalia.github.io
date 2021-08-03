package monkeys;


import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
class AttractionModelAssembler implements RepresentationModelAssembler<Attraction, EntityModel<Attraction>>{

    @Override
    public EntityModel<Attraction> toModel(Attraction attraction) {
        return EntityModel.of(attraction, linkTo(methodOn(AttractionController.class).one(attraction.getId())).withSelfRel(),
                linkTo(methodOn(AttractionController.class).all()).withRel("attractions"));
    }

    @Override
    public CollectionModel<EntityModel<Attraction>> toCollectionModel(Iterable<? extends Attraction> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
