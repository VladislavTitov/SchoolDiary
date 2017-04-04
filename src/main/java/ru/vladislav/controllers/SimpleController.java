package ru.vladislav.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/")
public class SimpleController {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String getSomething(){
        return "something";
    }

}
