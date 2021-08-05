package org.kurento.tutorial.groupcall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    private String home() {
        return ("index");
    }
}
