package ManyToOne.java.ManyToOne.documentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * controller of the open api
 * 
 * 
 * @author Aissatou Bobo DIALLO

 * @version 0.0.0
 * @since 0.0.0
 */

@Controller
public class SwaggerConfig {
	
	@RequestMapping("/")
    public String swagger() {
        return "redirect:swagger-ui.html";
    }
}
