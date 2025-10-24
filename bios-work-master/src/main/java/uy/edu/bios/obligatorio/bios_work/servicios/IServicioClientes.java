package uy.edu.bios.obligatorio.bios_work.servicios;

import uy.edu.bios.obligatorio.bios_work.dominio.Cliente;
import uy.edu.bios.obligatorio.bios_work.excepciones.ExcepcionBiosWork;

import java.util.List;


public interface IServicioClientes {

    List<Cliente> Listar();

    Cliente obtener(String nombreUsuario);

    void agregar(Cliente usuario) throws ExcepcionBiosWork;

    void modificar(Cliente usuario) throws ExcepcionBiosWork;

    void eliminar(String nombreUsuario) throws ExcepcionBiosWork;

}