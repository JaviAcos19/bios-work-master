package uy.edu.bios.obligatorio.bios_work.servicios;

import uy.edu.bios.obligatorio.bios_work.dominio.OfertaTrabajo;
import uy.edu.bios.obligatorio.bios_work.dominio.Postulacion;
import uy.edu.bios.obligatorio.bios_work.especificaciones.EspecificacionesOfertaTrabajo;
import uy.edu.bios.obligatorio.bios_work.excepciones.ExcepcionBiosWork;
import uy.edu.bios.obligatorio.bios_work.excepciones.ExcepcionNoExiste;
import uy.edu.bios.obligatorio.bios_work.excepciones.ExcepcionYaExiste;
import uy.edu.bios.obligatorio.bios_work.repositorios.IRepositorioOfertasTrabajo;
import uy.edu.bios.obligatorio.bios_work.repositorios.IRepositorioPostulaciones;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ServicioOfertasTrabajo implements IServicioOfertasTrabajo{

    @Autowired
    private IRepositorioOfertasTrabajo repositorioOfertasTrabajo;
    
    @Autowired
    private IRepositorioPostulaciones repositorioPostulaciones;

    @Override
    public List<OfertaTrabajo> listarTodas() {
        return repositorioOfertasTrabajo.findAll();
    }

    @Override
    public List<OfertaTrabajo> listarVigentes() {
        return repositorioOfertasTrabajo.findAll(EspecificacionesOfertaTrabajo.listarVigentes());
    }

    @Override
    public List<OfertaTrabajo> listarPorCliente(String nombreUsuario) {
        
        if (nombreUsuario == null || nombreUsuario.isBlank()) return null;

        return repositorioOfertasTrabajo.findByCliente_NombreUsuario(nombreUsuario);
    }

    @Override
    public OfertaTrabajo obtener(Long id) {
        return repositorioOfertasTrabajo.findById(id).orElse(null);
    }

    @Override
    public void agregar(OfertaTrabajo ofertaTrabajo) throws ExcepcionBiosWork {

        OfertaTrabajo ofertaTrabajoExistente = repositorioOfertasTrabajo.findById(ofertaTrabajo.getId()).orElse(null);

        if (ofertaTrabajoExistente != null) {
            throw new ExcepcionYaExiste("La oferta de trabajo ingresada ya se encuentra registrada en el sistema.");
        }

        if (ofertaTrabajo.getFechaCierre().before(ofertaTrabajo.getFechaPublicacion()) || ofertaTrabajo.getFechaCierre().equals(ofertaTrabajo.getFechaPublicacion())) {
            throw new ExcepcionBiosWork("La fecha de cierre debe ser posterior a la fecha de publicación.");
        }

        repositorioOfertasTrabajo.save(ofertaTrabajo);
    }

    @Override
    public void modificar(OfertaTrabajo ofertaTrabajo) throws ExcepcionBiosWork {
        
        OfertaTrabajo ofertaTrabajoExistente = repositorioOfertasTrabajo.findById(ofertaTrabajo.getId()).orElse(null);

        if (ofertaTrabajoExistente == null) {
             throw new ExcepcionNoExiste("La oferta de trabajo ingresada no se encuentra registrada en el sistema.");
        }

        if (ofertaTrabajo.getFechaCierre().before(ofertaTrabajo.getFechaPublicacion()) || ofertaTrabajo.getFechaCierre().equals(ofertaTrabajo.getFechaPublicacion())) {
            throw new ExcepcionBiosWork("La fecha de cierre debe ser posterior a la fecha de publicación.");
        }

        repositorioOfertasTrabajo.save(ofertaTrabajo);
    }

    @Override
    public void eliminar(Long id) throws ExcepcionBiosWork {
        
        OfertaTrabajo ofertaTrabajoExistente = repositorioOfertasTrabajo.findById(id).orElse(null);

        if (ofertaTrabajoExistente == null) {
             throw new ExcepcionNoExiste("La oferta de trabajo ingresada no se encuentra registrada en el sistema.");
        }

        List<Postulacion> postulaciones = ofertaTrabajoExistente.getPostulaciones();

        for (Postulacion postulacion : postulaciones) {

            Long idPostulacion = postulacion.getId();
            repositorioPostulaciones.deleteById(idPostulacion);
        }

        repositorioOfertasTrabajo.deleteById(id);
    }

}