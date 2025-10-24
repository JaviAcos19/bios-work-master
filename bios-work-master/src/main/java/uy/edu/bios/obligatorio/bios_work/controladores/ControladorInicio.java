package uy.edu.bios.obligatorio.bios_work.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/")
public class ControladorInicio {

    @GetMapping
    public String mostrarInicio() {
        return "inicio/index";
    }
    

}