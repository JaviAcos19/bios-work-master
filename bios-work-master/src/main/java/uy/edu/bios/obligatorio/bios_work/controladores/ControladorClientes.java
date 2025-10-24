package uy.edu.bios.obligatorio.bios_work.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import uy.edu.bios.obligatorio.bios_work.dominio.Cliente;
import uy.edu.bios.obligatorio.bios_work.servicios.IServicioClientes;
import uy.edu.bios.obligatorio.bios_work.excepciones.ExcepcionBiosWork;

@Controller
@RequestMapping("/clientes")
public class ControladorClientes {
    
    @Autowired
    private IServicioClientes servicioClientes;

    @GetMapping
    public String mostrarIndex(String nombreUsuario, Model model) {
    List<Cliente> clientes;

    if (nombreUsuario != null && !nombreUsuario.trim().isEmpty()) {
        Cliente cliente = servicioClientes.obtener(nombreUsuario);
        if (cliente != null) {
            clientes = List.of(cliente); // convierte un solo cliente en lista
        } else {
            clientes = List.of(); // lista vacía si no existe
            model.addAttribute("mensaje", "No se encontró ningún cliente con ese nombre de usuario.");
        }
    } else {
        clientes = servicioClientes.Listar(); // todos los clientes
    }

    model.addAttribute("clientes", clientes); 
    return "clientes/index";
}
    
    @GetMapping("/agregar")
    public String mostrarAgregar(Model model) {
         model.addAttribute("cliente", new Cliente());

        return "clientes/agregar";
    }
    
    @PostMapping("/agregar")
    public String procesarAgregar(@ModelAttribute @Valid Cliente cliente,BindingResult result, Model model,RedirectAttributes attributes) {
    
        if (result.hasErrors()) {
            model.addAttribute("mensaje", "Error en el ingreso de datos");
        }
        
        try{
            servicioClientes.agregar(cliente);
            attributes.addFlashAttribute("mensaje","Cliente agregado con exito");

            return "redirect:/clientes";
        }
        catch (ExcepcionBiosWork e){
            model.addAttribute("mensaje", "¡ERROR! " + e.getMessage());

            return "clientes/agregar";
        }
        catch (Exception e) {
            model.addAttribute("mensaje", "Ocurrió un error inesperado al agregar el cliente.");
            return "clientes/agregar";
        }   
 
}


    // Mostrar formulario de modificación
    @GetMapping("/modificar/{nombreUsuario}")
    public String mostrarModificar(@PathVariable String nombreUsuario, Model model, RedirectAttributes attributes) {

    Cliente existente = servicioClientes.obtener(nombreUsuario);
    if (existente == null || Boolean.FALSE.equals(existente.getDisponible())) {
        attributes.addFlashAttribute("mensaje", "¡ERROR! El cliente no existe o no está disponible.");
        return "redirect:/clientes";
    }

    

    model.addAttribute("cliente", existente);
    model.addAttribute("campoactivo", true);
    model.addAttribute("foco", "nombre");
    return "clientes/modificar";
}

    // Procesar modificación
    @PostMapping("/modificar/{nombreUsuario}")
    public String procesarModificar(@PathVariable String nombreUsuario, @ModelAttribute("cliente") @Valid Cliente clienteFormulario, BindingResult result, Model model, RedirectAttributes attributes) {

    if (result.hasErrors()) {
        model.addAttribute("mensaje", "Error en el ingreso de datos");
        model.addAttribute("campoactivo", true);
        return "clientes/modificar";
    }


    try {
        Cliente existente = servicioClientes.obtener(nombreUsuario);
        if (existente == null || Boolean.FALSE.equals(existente.getDisponible())) {
            model.addAttribute("mensaje", "¡ERROR! El cliente no existe o no está disponible.");
            model.addAttribute("campoactivo", true);
            return "clientes/modificar";
        }

        // Copiamos SOLO lo editable
        existente.setClaveAcceso(clienteFormulario.getClaveAcceso());
        existente.setNombre(clienteFormulario.getNombre());

        if (clienteFormulario.getSitioWeb() != null && clienteFormulario.getSitioWeb().isBlank()) {
            existente.setSitioWeb(null);
        } else {
            existente.setSitioWeb(clienteFormulario.getSitioWeb());
        }

        existente.setDisponible(
            clienteFormulario.getDisponible() != null ? clienteFormulario.getDisponible() : existente.getDisponible()
        );

        servicioClientes.modificar(existente);

        attributes.addFlashAttribute("mensaje", "Cliente modificado con éxito");
        return "redirect:/clientes";

    } catch (ExcepcionBiosWork e) {
        model.addAttribute("mensaje", "¡ERROR! " + e.getMessage());
        model.addAttribute("campoactivo", true);
        return "clientes/modificar";
    } catch (Exception e) {
        e.printStackTrace();
        model.addAttribute("mensaje", "Ocurrió un error inesperado al modificar el cliente: " + e.getMessage());
        model.addAttribute("campoactivo", true);
        return "clientes/modificar";
    }
}

    @GetMapping("/eliminar")
    public String mostrarEliminar(@ModelAttribute Cliente clientes, Model model) {
        
        return "clientes/eliminar";
    }
}
