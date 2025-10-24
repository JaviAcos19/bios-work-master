package uy.edu.bios.obligatorio.bios_work.servicios;

import uy.edu.bios.obligatorio.bios_work.dominio.OfertaTrabajo;
import uy.edu.bios.obligatorio.bios_work.dominio.Postulacion;
import uy.edu.bios.obligatorio.bios_work.excepciones.ExcepcionBiosWork;
import uy.edu.bios.obligatorio.bios_work.excepciones.ExcepcionNoExiste;
import uy.edu.bios.obligatorio.bios_work.excepciones.ExcepcionYaExiste;
import uy.edu.bios.obligatorio.bios_work.repositorios.IRepositorioPostulaciones;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ServicioPostulaciones implements IServicioPostulaciones{

    @Autowired
    private IRepositorioPostulaciones repositorioPostulaciones;
    
    @Override
    public List<Postulacion> listar() {
        return repositorioPostulaciones.findAll();
    }

    @Override
    public List<Postulacion> listarPorOfertaTrabajo(Long idOfertaTrabajo) {
        return repositorioPostulaciones.findByOfertaTrabajo_Id(idOfertaTrabajo);
    }

    @Override
    public Postulacion obtener(Long id) {
        return repositorioPostulaciones.findById(id).orElse(null);
    }

    @Override
    public void agregar(Postulacion postulacion) throws ExcepcionBiosWork {

        Postulacion postulacionExistente = repositorioPostulaciones.findById(postulacion.getId()).orElse(null);

        if (postulacionExistente != null) {
            throw new ExcepcionYaExiste("La postulación ingresada ya se encuentra registrada en el sistema.");
        }

        OfertaTrabajo ofertaTrabajo = postulacion.getOfertaTrabajo();
        Date fechaPostulacion = postulacion.getFechaPostulacion();
        
        if (fechaPostulacion.before(ofertaTrabajo.getFechaPublicacion()) || fechaPostulacion.after(ofertaTrabajo.getFechaCierre())) {
            throw new ExcepcionBiosWork("La fecha de postulación debe estar comprendida dentro del período definido en la oferta de trabajo.");
        }

        repositorioPostulaciones.save(postulacion);
    }

    @Override
    public void eliminar(Long id) throws ExcepcionBiosWork {
        
        Postulacion postulacionExistente = repositorioPostulaciones.findById(id).orElse(null);

        if (postulacionExistente == null) {
             throw new ExcepcionNoExiste("La postulación ingresada no se encuentra registrada en el sistema.");
        }
        repositorioPostulaciones.deleteById(id);
    }

}