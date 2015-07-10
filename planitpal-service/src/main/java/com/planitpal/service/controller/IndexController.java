package com.planitpal.service.controller;

import com.planitpal.service.domain.repository.PlanitpalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.planitpal.service.domain.Plan.plan;

@Controller
@Slf4j
public class IndexController {

    @Autowired
    private PlanitpalRepository planitpalRepository;
    @SuppressWarnings("SameReturnValue")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String showIndex() {
        log.debug("hello my name is Johnny");
        log.info("Hello world project");
        planitpalRepository.save(plan("johnny"));
        return "Hello world 2";
    }

}
