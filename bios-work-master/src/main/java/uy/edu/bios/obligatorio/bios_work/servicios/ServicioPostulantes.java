package uy.edu.bios.obligatorio.bios_work.servicios;

import uy.edu.bios.obligatorio.bios_work.dominio.Postulante;
import uy.edu.bios.obligatorio.bios_work.dominio.Postulacion;
import uy.edu.bios.obligatorio.bios_work.excepciones.ExcepcionBiosWork;
import uy.edu.bios.obligatorio.bios_work.excepciones.ExcepcionNoExiste;
import uy.edu.bios.obligatorio.bios_work.excepciones.ExcepcionYaExiste;
import uy.edu.bios.obligatorio.bios_work.repositorios.IRepositorioPostulantes;
import uy.edu.bios.obligatorio.bios_work.repositorios.IRepositorioPostulaciones;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;


@Service
public class ServicioPostulantes implements IServicioPostulantes{

    @Autowired
    private IRepositorioPostulantes repositorioPostulantes;
    
    @Autowired
    private IRepositorioPostulaciones repositorioPostulaciones;

    @Override
    public List<Postulante> Listar() {
        return repositorioPostulantes.findAll();
    }

    @Override
    public Postulante obtener(String nombreUsuario) {
        return repositorioPostulantes.findById(nombreUsuario).orElse(null);
    }

    @Override
    @Transactional
    public void agregar(Postulante postulante) throws ExcepcionBiosWork {

        Postulante postulanteExistente = repositorioPostulantes.findById(postulante.getNombreUsuario()).orElse(null);

        if (postulanteExistente != null) {
            throw new ExcepcionYaExiste("El postulante ingresado ya se encuentra registrado en el sistema.");
        }

        repositorioPostulantes.save(postulante);
    }

    @Override
    @Transactional
    public void modificar(Postulante postulante) throws ExcepcionBiosWork {
        
        Postulante postulanteExistente = repositorioPostulantes.findById(postulante.getNombreUsuario()).orElse(null);

        if (postulanteExistente == null) {
             throw new ExcepcionNoExiste("El postulante ingresado no se encuentra registrado en el sistema.");
        }

        repositorioPostulantes.save(postulante);
    }

    @Override
    @Transactional
    public void eliminar(String nombreUsuario) throws ExcepcionBiosWork {
        
        Postulante postulanteExistente = repositorioPostulantes.findById(nombreUsuario).orElse(null);

        if (postulanteExistente == null) {
             throw new ExcepcionNoExiste("El postulante ingresado no se encuentra registrado en el sistema.");
        }

        List<Postulacion> postulaciones = postulanteExistente.getPostulaciones();

        for (Postulacion postulacion : postulaciones) {

            Long idPostulacion = postulacion.getId();
            repositorioPostulaciones.deleteById(idPostulacion);
        }

        repositorioPostulantes.deleteById(nombreUsuario);
    }
    
}