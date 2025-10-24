package uy.edu.bios.obligatorio.bios_work.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import uy.edu.bios.obligatorio.bios_work.dominio.Area;
import uy.edu.bios.obligatorio.bios_work.excepciones.ExcepcionBiosWork;
import uy.edu.bios.obligatorio.bios_work.servicios.IServicioAreas;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/areas")
public class ControladorAreas {
    
    @Autowired
    private IServicioAreas servicioAreas;

    @GetMapping
    public String mostrarInicio(@RequestParam(required = false)String criterio, Model model){
        List<Area> areas = servicioAreas.buscar(criterio);

        model.addAttribute("areas", areas);

        return "areas/index";
    }

    @GetMapping("/agregar")
    public String mostrarAgregar(Model model) {

        model.addAttribute("area", new Area());
        model.addAttribute("titulo", "Agregar Nueva Área");
        model.addAttribute("textoBoton", "Guardar");

        return "areas/agregar";
    }

    @PostMapping("/agregar")
    public String agregar(@Valid @ModelAttribute Area area, BindingResult result,
                          RedirectAttributes redirect, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Agregar Nueva Área");
            model.addAttribute("textoBoton", "Guardar");
            return "areas/agregar";
        }

        try {
            servicioAreas.agregar(area);
            redirect.addFlashAttribute("mensaje", "Área agregada correctamente.");
            return "redirect:/areas";
        } catch (ExcepcionBiosWork e) {
            model.addAttribute("mensaje", e.getMessage());
            return "areas/agregar";
        }
    }

    @GetMapping("/{id}")
    public String mostrarDetalle(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        Area area = servicioAreas.obtener(id);
        if (area == null) {
            redirect.addFlashAttribute("mensaje", "El área no fue encontrada.");
            return "redirect:/areas";
        }
        model.addAttribute("area", area);
        model.addAttribute("titulo", "Detalle del Área");
        return "areas/detalle";

    }

    @GetMapping("/eliminar")
    public String mostrarEliminar(@RequestParam("id") Long id, Model model, RedirectAttributes redirect) {
        Area area = servicioAreas.obtener(id);
        if (area == null) {
            redirect.addFlashAttribute("mensaje", "No se encontró el área.");
            return "redirect:/areas";
        }

        model.addAttribute("area", area);
        model.addAttribute("titulo", "Eliminar Área");
        model.addAttribute("textoBoton", "Eliminar");
        return "areas/eliminar";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id, RedirectAttributes redirect) {
        try {
            servicioAreas.eliminar(id);
            redirect.addFlashAttribute("mensaje", "Área eliminada correctamente.");
        } catch (ExcepcionBiosWork e) {
            redirect.addFlashAttribute("mensaje", e.getMessage());
        }
        return "redirect:/areas";
    }

    @GetMapping("/modificar/{id}")
public String mostrarModificar(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
    Area area = servicioAreas.obtener(id);

    if (area == null) {
        redirect.addFlashAttribute("mensaje", "El área no fue encontrada.");
        return "redirect:/areas";
    }

    model.addAttribute("area", area);
    model.addAttribute("titulo", "Modificar Área");
    model.addAttribute("textoBoton", "Modificar");

    return "areas/modificar";
}

@PostMapping("/modificar")
public String modificar(@Valid @ModelAttribute Area area, BindingResult result,
                        RedirectAttributes redirect, Model model) {
    if (result.hasErrors()) {
        model.addAttribute("titulo", "Modificar Área");
        model.addAttribute("textoBoton", "Modificar");
        return "areas/modificar";
    }

    try {
        servicioAreas.modificar(area);
        redirect.addFlashAttribute("mensaje", "Área modificada correctamente.");
        return "redirect:/areas";
    } catch (ExcepcionBiosWork e) {
        model.addAttribute("mensaje", e.getMessage());
        return "areas/modificar";
    }
}

}