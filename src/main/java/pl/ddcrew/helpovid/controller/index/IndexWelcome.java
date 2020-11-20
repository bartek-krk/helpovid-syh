package pl.ddcrew.helpovid.controller.index;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class IndexWelcome {
    String name = "Helpovid";
    String team = "DD CREW";
}
