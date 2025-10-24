package uy.edu.bios.obligatorio.bios_work.controladores;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


//Controlador que maneja las excepciones genericas del sistema
@ControllerAdvice(basePackageClasses = ControladorCompartido.class)
public class ControladorCompartido {
    
    @ExceptionHandler
    public String manejarExcepcion(Exception e, Model model) {
        model.addAttribute("mensaje", "No se pudo procesar la solicitud.");


        return "excepcion";
    }

}
