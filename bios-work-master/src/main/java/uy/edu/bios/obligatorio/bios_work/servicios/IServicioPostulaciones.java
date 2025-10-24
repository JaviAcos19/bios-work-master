package uy.edu.bios.obligatorio.bios_work.servicios;

import uy.edu.bios.obligatorio.bios_work.dominio.Postulacion;
import uy.edu.bios.obligatorio.bios_work.excepciones.ExcepcionBiosWork;

import java.util.List;


public interface IServicioPostulaciones {

    List<Postulacion> listar();

    List<Postulacion> listarPorOfertaTrabajo(Long idOfertaTrabajo);

    Postulacion obtener(Long id);

    void agregar(Postulacion postulacion) throws ExcepcionBiosWork;

    void eliminar(Long id) throws ExcepcionBiosWork;

}