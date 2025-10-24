package uy.edu.bios.obligatorio.bios_work.repositorios;

import uy.edu.bios.obligatorio.bios_work.dominio.OfertaTrabajo;

import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface IRepositorioOfertasTrabajo extends JpaRepository<OfertaTrabajo,Long>, JpaSpecificationExecutor<OfertaTrabajo> {

    @Override
    @EntityGraph(type=EntityGraphType.LOAD, attributePaths={"area","cliente"})
    List<OfertaTrabajo> findAll();

    @Override
    @EntityGraph(type=EntityGraphType.LOAD, attributePaths={"area","cliente"})
    List<OfertaTrabajo> findAll(Specification<OfertaTrabajo> spec);

    @EntityGraph(type=EntityGraphType.LOAD, attributePaths={"area","cliente"})
    List<OfertaTrabajo> findByCliente_NombreUsuario(String nombreUsuario);

}