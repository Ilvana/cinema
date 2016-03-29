package kino.controller;

import kino.utils.JsonMessageGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "index";
    }

    @RequestMapping(value = "/testRest", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Map testREST() {
        return JsonMessageGenerator.generateMessage("Message", "Hello world from JSON.");
    }
}
