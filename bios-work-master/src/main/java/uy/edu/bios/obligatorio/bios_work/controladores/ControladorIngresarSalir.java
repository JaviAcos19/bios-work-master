package uy.edu.bios.obligatorio.bios_work.controladores;

import java.security.Principal;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorIngresarSalir {
    @GetMapping("/login")
    public String ingresar(Principal principal) {
        if (principal == null || principal instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String salir() {
        return "logout";
    }
}
