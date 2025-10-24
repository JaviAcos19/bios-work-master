package uy.edu.bios.obligatorio.bios_work.repositorios;

import uy.edu.bios.obligatorio.bios_work.dominio.Consultor;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.lang.Nullable;


public interface IRepositorioConsultores extends JpaRepository<Consultor,String>,JpaSpecificationExecutor<Consultor> {
    
    @Override
    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = {"roles"})
    List<Consultor> findAll();

    @Override
    @EntityGraph(type = EntityGraphType.LOAD,attributePaths = {"roles"})
    List<Consultor> findAll(@Nullable Specification<Consultor> spec);
    
    @Override
    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = {"roles"})
    Optional<Consultor> findById(String id);

}