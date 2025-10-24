package uy.edu.bios.obligatorio.bios_work.repositorios;

import uy.edu.bios.obligatorio.bios_work.dominio.Postulacion;

import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IRepositorioPostulaciones extends JpaRepository<Postulacion,Long> {

    @EntityGraph(type=EntityGraphType.LOAD, attributePaths={"postulante", "oferta_trabajo"})
    List<Postulacion> findByOfertaTrabajo_Id(Long idOfertaTrabajo);

}