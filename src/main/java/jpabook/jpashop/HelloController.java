package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") //serverside rendering
    public String hello(Model model){
        model.addAttribute("data","hello!!!");
        return "hello"; // view name , resources/templates/hello.html, thymeleaf viewName Mapping

    }
}
