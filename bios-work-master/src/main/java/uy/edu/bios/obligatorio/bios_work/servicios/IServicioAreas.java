package uy.edu.bios.obligatorio.bios_work.servicios;

import uy.edu.bios.obligatorio.bios_work.dominio.Area;
import uy.edu.bios.obligatorio.bios_work.excepciones.ExcepcionBiosWork;

import java.util.List;


public interface IServicioAreas {

    List<Area> listar();

    List<Area> buscar(String criterio);

    Area obtener(Long id);
    
    void agregar(Area area) throws ExcepcionBiosWork;

    void modificar(Area area) throws ExcepcionBiosWork;
    
    void eliminar(Long id) throws ExcepcionBiosWork;

}