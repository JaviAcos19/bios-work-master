package uy.edu.bios.obligatorio.bios_work.repositorios;

import uy.edu.bios.obligatorio.bios_work.dominio.Postulante;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IRepositorioPostulantes extends JpaRepository<Postulante,String> {
    
    @Override
    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = {"roles"})
    List<Postulante> findAll();

    @Override
    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = {"roles"})
    Optional<Postulante> findById(String nombreUsuario);
    
}