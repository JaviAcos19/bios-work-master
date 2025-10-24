package uy.edu.bios.obligatorio.bios_work.repositorios;

import uy.edu.bios.obligatorio.bios_work.dominio.Area;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface IRepositorioAreas extends JpaRepository<Area,Long>, JpaSpecificationExecutor<Area> {

    Area findByNombre(String nombre);
}