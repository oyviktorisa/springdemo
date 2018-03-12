package baseapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController
{
    Logger log = LoggerFactory.getLogger (this.getClass ());


    @RequestMapping(value = { "/hello", "/home" }, method = RequestMethod.GET)
    public String getHello (Model model)
    {
        return "hello";
    }


    @RequestMapping(value = "/success", method = RequestMethod.POST)
    public String successLogin (Model model)
    {
        return "redirect:/hello";
    }


    
}
