
package com.carepulse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/carepulse"})
    public String home() {
        return "forward:/index.html";
    }
}
