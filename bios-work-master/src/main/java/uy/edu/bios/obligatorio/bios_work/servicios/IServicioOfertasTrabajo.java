package uy.edu.bios.obligatorio.bios_work.servicios;

import uy.edu.bios.obligatorio.bios_work.dominio.OfertaTrabajo;
import uy.edu.bios.obligatorio.bios_work.excepciones.ExcepcionBiosWork;

import java.util.List;


public interface IServicioOfertasTrabajo {

    List<OfertaTrabajo> listarTodas();

    List<OfertaTrabajo> listarVigentes();

    List<OfertaTrabajo> listarPorCliente(String nombreUsuario);

    OfertaTrabajo obtener(Long id);

    void agregar(OfertaTrabajo ofertaTrabajo) throws ExcepcionBiosWork;

    void modificar(OfertaTrabajo ofertaTrabajo) throws ExcepcionBiosWork;

    void eliminar(Long id) throws ExcepcionBiosWork;

}