package gafelix.alurabackendcourse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWordController {

    @GetMapping("/")
    @ResponseBody
    public String helloWorld() { return "Hello, world :)!"; }

}
