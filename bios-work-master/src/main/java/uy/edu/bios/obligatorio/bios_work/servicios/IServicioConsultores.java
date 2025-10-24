package uy.edu.bios.obligatorio.bios_work.servicios;

import uy.edu.bios.obligatorio.bios_work.dominio.Consultor;
import uy.edu.bios.obligatorio.bios_work.excepciones.ExcepcionBiosWork;

import java.util.List;


public interface IServicioConsultores {

    List<Consultor> Listar();

    List<Consultor> buscar(String criterio);

    Consultor obtener(String nombreUsuario);

    void agregar(Consultor consultor) throws ExcepcionBiosWork;

    void modificar(Consultor consultor) throws ExcepcionBiosWork;

    void eliminar(String nombreUsuario) throws ExcepcionBiosWork;

}