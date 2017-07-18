package com.bootcamp.socialnetwork.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class ApplicationController {

    @RequestMapping("/")
    String home(ModelMap modal) {
        modal.addAttribute("title","Social Network");
        return "index";
    }

    @RequestMapping("/partials/{page}")
    String partialHandler(@PathVariable("page") final String page) {
        return page;
    }

//    @RequestMapping("/profile/{username}")
//    String profileHandler(@PathVariable("username") final String page) {
//        return page;
//    }
}
