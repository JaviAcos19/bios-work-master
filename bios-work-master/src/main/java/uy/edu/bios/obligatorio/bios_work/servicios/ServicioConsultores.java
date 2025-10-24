package uy.edu.bios.obligatorio.bios_work.servicios;

import uy.edu.bios.obligatorio.bios_work.dominio.Consultor;
import uy.edu.bios.obligatorio.bios_work.especificaciones.EspecificacionesConsultor;
import uy.edu.bios.obligatorio.bios_work.excepciones.ExcepcionBiosWork;
import uy.edu.bios.obligatorio.bios_work.excepciones.ExcepcionNoExiste;
import uy.edu.bios.obligatorio.bios_work.excepciones.ExcepcionYaExiste;
import uy.edu.bios.obligatorio.bios_work.repositorios.IRepositorioConsultores;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;


@Service
public class ServicioConsultores implements IServicioConsultores{

    @Autowired
    private IRepositorioConsultores repositorioConsultores;
    
    @Override
    public List<Consultor> Listar() {
        return repositorioConsultores.findAll();
    }

    @Override
    public List<Consultor> buscar(String criterio) {
        return repositorioConsultores.findAll(EspecificacionesConsultor.buscar(criterio));
    }    

    @Override
    public Consultor obtener(String nombreUsuario) {
        return repositorioConsultores.findById(nombreUsuario).orElse(null);
    }

    @Override
    @Transactional
    public void agregar(Consultor consultor) throws ExcepcionBiosWork {

        Consultor consultorExistente = repositorioConsultores.findById(consultor.getNombreUsuario()).orElse(null);

        if (consultorExistente != null) {
            throw new ExcepcionYaExiste("El consultor ingresado ya se encuentra registrado en el sistema.");
        }

        repositorioConsultores.save(consultor);
    }

    @Override
    @Transactional
    public void modificar(Consultor consultor) throws ExcepcionBiosWork {
        
        Consultor consultorExistente = repositorioConsultores.findById(consultor.getNombreUsuario()).orElse(null);

        if (consultorExistente == null) {
             throw new ExcepcionNoExiste("El consultor ingresado no se encuentra registrado en el sistema.");
        }

        repositorioConsultores.save(consultor);
    }

    @Override
    @Transactional
    public void eliminar(String nombreUsuario) throws ExcepcionBiosWork {
        
        Consultor consultorExistente = repositorioConsultores.findById(nombreUsuario).orElse(null);

        if (consultorExistente == null) {
             throw new ExcepcionNoExiste("El consultor ingresado no se encuentra registrado en el sistema.");
        }
        repositorioConsultores.deleteById(nombreUsuario);
    }
    
}