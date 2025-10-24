package uy.edu.bios.obligatorio.bios_work.servicios;

import uy.edu.bios.obligatorio.bios_work.dominio.Cliente;
import uy.edu.bios.obligatorio.bios_work.especificaciones.EspecificacionesCliente;
import uy.edu.bios.obligatorio.bios_work.excepciones.ExcepcionBiosWork;
import uy.edu.bios.obligatorio.bios_work.excepciones.ExcepcionNoExiste;
import uy.edu.bios.obligatorio.bios_work.excepciones.ExcepcionYaExiste;
import uy.edu.bios.obligatorio.bios_work.repositorios.IRepositorioClientes;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;


@Service
public class ServicioClientes implements IServicioClientes{

    @Autowired
    private IRepositorioClientes repositorioClientes;
    
    @Override
    public List<Cliente> Listar() {
        return repositorioClientes.findAll();
    }


    @Override
    public Cliente obtener(String nombreUsuario) {
        return repositorioClientes.findById(nombreUsuario).orElse(null);
    }

    @Override
    @Transactional
    public void agregar(Cliente cliente) throws ExcepcionBiosWork {

        Cliente clienteExistente = repositorioClientes.findById(cliente.getNombreUsuario()).orElse(null);

        if (clienteExistente != null && clienteExistente.getDisponible() == true) {
            throw new ExcepcionYaExiste("El cliente ingresado ya se encuentra registrado en el sistema.");
        }

        repositorioClientes.save(cliente);
    }

    @Override
    @Transactional
    public void modificar(Cliente cliente) throws ExcepcionBiosWork {
        
        Cliente clienteExistente = repositorioClientes.findById(cliente.getNombreUsuario()).orElse(null);

        if (clienteExistente == null) {
             throw new ExcepcionNoExiste("El cliente ingresado no se encuentra registrado en el sistema.");
        }

        if (clienteExistente != null && clienteExistente.getDisponible() == false) {
            throw new ExcepcionNoExiste("El cliente ingresado no se encuentra registrado en el sistema.");
        }

        repositorioClientes.save(cliente);
    }

    @Override
    @Transactional
    public void eliminar(String nombreUsuario) throws ExcepcionBiosWork {
        
        Cliente clienteExistente = repositorioClientes.findById(nombreUsuario).orElse(null);

        if (clienteExistente == null) {
             throw new ExcepcionNoExiste("El cliente ingresado no se encuentra registrado en el sistema.");
        }

        if (clienteExistente != null && clienteExistente.getDisponible() == false) {
            throw new ExcepcionNoExiste("El cliente ingresado no se encuentra registrado en el sistema.");
        }

        if (clienteExistente.getOfertasTrabajo() != null && !clienteExistente.getOfertasTrabajo().isEmpty()) {
            clienteExistente.setDisponible(false);
            repositorioClientes.save(clienteExistente);
        }

        repositorioClientes.deleteById(nombreUsuario);
    }
    
}