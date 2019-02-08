package com.my.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
public class AppModelResourceAssembler implements ResourceAssembler<AppModel, Resource<AppModel>> {
    
    public Resource toResource(AppModel entity) {
        Resource<AppModel> resource = new Resource<>(entity);
        addLinks(resource);
        return resource;
    }
    public void addLinks(Resource<AppModel> resource) {
        resource.add(linkTo(AppController2.class).slash(resource.getContent()).withSelfRel());
    }
}