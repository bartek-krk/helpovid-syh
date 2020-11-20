package pl.ddcrew.helpovid.controller.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class IndexController {
    @Autowired
    IndexWelcome indexWelcome;

    @GetMapping
    public IndexWelcome index(){
        return indexWelcome;
    }
}
