package com.planitpal.plan.templateservice.controller;

import com.planitpal.plan.templateservice.domain.Example;
import com.planitpal.plan.templateservice.domain.repository.ExampleRepository;
import com.planitpal.plan.templateservice.exceptions.ExampleNotFoundException;
import com.planitpal.plan.templateservice.view.ExampleResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/examples")
public class ExampleController {

    private ExampleRepository exampleRepository;

    @Autowired
    public ExampleController(ExampleRepository exampleRepository) {
        this.exampleRepository = exampleRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ExampleResource create(@RequestBody ExampleResource resource) {
        Example example = resource.createStyle();
        exampleRepository.save(example);
        return resource;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public Iterable<Example> searchAll() {
        return exampleRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Example find(@PathVariable String id) throws ExampleNotFoundException {
        Example foundExample = exampleRepository.findOne(id);
        if (foundExample == null)
            throw new ExampleNotFoundException("Style not found.");
        return foundExample;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void delete(@PathVariable String id) {
        exampleRepository.delete(id);
    }

}