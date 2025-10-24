package uy.edu.bios.obligatorio.bios_work.servicios;

import uy.edu.bios.obligatorio.bios_work.dominio.Postulante;
import uy.edu.bios.obligatorio.bios_work.excepciones.ExcepcionBiosWork;

import java.util.List;


public interface IServicioPostulantes {

    List<Postulante> Listar();

    Postulante obtener(String nombreUsuario);

    void agregar(Postulante postulante) throws ExcepcionBiosWork;

    void modificar(Postulante postulante) throws ExcepcionBiosWork;

    void eliminar(String nombreUsuario) throws ExcepcionBiosWork;

}