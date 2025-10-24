package uy.edu.bios.obligatorio.bios_work.controladores;

import uy.edu.bios.obligatorio.bios_work.dominio.Consultor;
import uy.edu.bios.obligatorio.bios_work.excepciones.ExcepcionBiosWork;
import uy.edu.bios.obligatorio.bios_work.servicios.IServicioConsultores;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/consultores")
public class ControladorConsultores {

    @Autowired
    private IServicioConsultores servicioConsultores;

    @GetMapping
    public String mostrarIndex(String nombreUsuario, Model model) {
        List<Consultor> consultores;

        if (nombreUsuario != null && !nombreUsuario.trim().isEmpty()) {
        
            Consultor consultor = servicioConsultores.obtener(nombreUsuario);
            
            if (consultor != null) {

            consultores = List.of(consultor); // convierte un solo consultor en lista
            
            } else {

                consultores = List.of(); // lista vacía si no existe
                model.addAttribute("mensaje", "No se encontró ningún consultor con ese nombre de usuario.");
            }
        
        } else {
            consultores = servicioConsultores.Listar(); // todos los consultores
        }

        model.addAttribute("consultores", consultores); 
        return "consultores/index";
    }

    @GetMapping("/agregar")
    public String mostrarAgregar(Model model) {

        model.addAttribute("consultor", new Consultor());

        return "consultores/agregar";
    }
    
    @PostMapping("/agregar")
    public String procesarAgregar(@ModelAttribute @Valid Consultor consultor, BindingResult result, Model model, RedirectAttributes attributes) {
    
        if (result.hasErrors()) {
            model.addAttribute("mensaje", "Error en el ingreso de datos");
        }
        
        try {
            servicioConsultores.agregar(consultor);

            attributes.addFlashAttribute("mensaje","Consultor agregado con éxito");

            return "redirect:/consultores";
        } catch (ExcepcionBiosWork e){

            model.addAttribute("mensaje", "¡ERROR! " + e.getMessage());

            return "consultores/agregar";

        } catch (Exception e) {

            model.addAttribute("mensaje", "Ocurrió un error inesperado al agregar el consultor.");
            return "consultores/agregar";
        }    
    }

    @GetMapping("/eliminar")
    public String mostrarEliminar(@ModelAttribute Consultor consultor, Model model) {
        
        return "consultores/eliminar";
    }

}